package com.yeqifu.tool.threadpool;

import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author: yeqifu
 * @date: 2023/6/11 17:04
 */
public class ThreadPool {

    /**
     * 任务队列
     */
    private BlockingQueue<Queue> workQueue;

    /**
     * 线程集合
     */
    private HashSet<Worker> workers = new HashSet<>();

    /**
     * 核心线程数
     */
    private Integer coreSize;

    /**
     * 获取任务的超时时间
     */
    private Long timeout;

    /**
     * 超时时间的单位
     */
    private TimeUnit timeUnit;

    public ThreadPool(BlockingQueue<Queue> workQueue, Integer coreSize, Long timeout, TimeUnit timeUnit, Integer capacity) {
        this.workQueue = workQueue;
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.workQueue = new BlockingQueue<>(capacity);
    }

    class Worker {

    }
}