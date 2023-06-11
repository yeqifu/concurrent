package com.yeqifu.tool.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列类
 *
 * @author: yeqifu
 * @date: 2023/6/10 15:42
 */
@Slf4j
public class BlockingQueue<T> {

    /**
     * 声明一个阻塞队列 arrayDeque，arrayDeque 底层实现是双向链表
     */
    private Deque<T> arrayDeque = new ArrayDeque<>();

    /**
     * 声明一个锁，因为线程池里面的线程会去阻塞队列里面拿任务，线程池里面的多个线程在同一时间内是有可能拿到同一个任务的
     * 如果同一个任务能够同时被多个线程都拿到，那么这个任务就重复执行了，消费者这边是这样，生产者这边同理
     */
    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 生产者条件变量：生产者这端是为了防止生产者不断的往阻塞队列里面放任务（因为阻塞队列是有容量限制的）
     */
    private Condition fullWaitSet = reentrantLock.newCondition();

    /**
     * 消费者条件变量：消费者这端是当阻塞队列里没有任务时，让线程池中的线程等待
     */
    private Condition emptyWaitSet = reentrantLock.newCondition();

    /**
     * 阻塞队列容量
     */
    private Integer capacity;

    public BlockingQueue(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 超时获取阻塞队列中的内容
     *
     * @param timeout  时间
     * @param timeUnit 时间单位
     * @return
     */
    public T pollElement(Long timeout, TimeUnit timeUnit) {
        reentrantLock.lock();
        try {
            // 将超时时间转换成纳秒
            long nanos = timeUnit.toNanos(timeout);
            while (arrayDeque.isEmpty()) {
                try {
                    // 如果经过了 timeout 这么长的超时时间还没有获取到元素，那么返回 null，否则减去 nanos（awaitNanos()这个方法的意思是减去 nanos），产生新的超时时间
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    log.error("InterruptedException: \n{}\n{}", e.getMessage(), e);
                }
            }
            T t = arrayDeque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 从阻塞队列里面获取元素
     *
     * @return 阻塞队列里面的第一个元素
     */
    public T takeElement() {
        reentrantLock.lock();
        try {
            // 如果阻塞队列里面没有元素，那么消费者等待
            while (arrayDeque.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    log.error("InterruptedException: \n{}\n{}", e.getMessage(), e);
                }
            }
            // 如果阻塞队列里面有元素，那么返回阻塞队列里面的第一个元素，并将其从阻塞队列中删除
            T t = arrayDeque.removeFirst();
            // 唤醒生产者线程，表示阻塞队列中有一个新的空位产生，你可以放入新的元素进来了
            fullWaitSet.signal();
            return t;
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 往阻塞队列中放入一个新的元素
     *
     * @param t 新的元素
     */
    public void putElement(T t) {
        reentrantLock.lock();
        try {
            // 如果阻塞队列满了，那么生产者等待
            while (arrayDeque.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    log.error("InterruptedException: \n{}\n{}", e.getMessage(), e);
                }
            }
            // 如果阻塞队列可以放入元素，那么将元素放入阻塞队列的队尾
            arrayDeque.addLast(t);
            // 唤醒消费者线程，表明阻塞队列中有元素了，可以进行消费
            emptyWaitSet.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 获取阻塞队列中元素的个数，为什么获取元素的大小也要加锁，因为在你获取元素的时候有线程可能往阻塞队列中添加元素或取走元素
     *
     * @return 阻塞队列中元素的个数
     */
    public Integer getSize() {
        reentrantLock.lock();
        try {
            return arrayDeque.size();
        } finally {
            reentrantLock.unlock();
        }
    }
}