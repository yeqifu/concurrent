package com.yeqifu.test4;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用面向对象改进
 *
 * @Author: 落亦-
 * @Date: 2022/1/26 11:29
 */
@Slf4j
public class ResolveQuestionImprove {

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        // 等待t1和t2运行完毕
        t1.join();
        t2.join();

        log.debug("{}", room.getCount());

    }

}

class Room{
    private static int count = 0;

    /**
     * 添加操作
     */
    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    /**
     * 减法操作
     */
    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    /**
     * 获取count的值
     *
     * @return  count的值
     */
    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}