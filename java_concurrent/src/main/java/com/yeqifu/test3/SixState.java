package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程的六种状态：new、runnable、blocked、wait（无时限的等待）、timed_waiting（有时限的等待）、terminated
 *
 * @Author: 落亦-
 * @Date: 2022/1/23 12:51
 */
@Slf4j
public class SixState {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            // t1的状态为：new ，不调用start()方法
            @Override
            public void run() {

            }
        };

        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
                // t2是runnable状态
                while (true) {

                }
            }
        };

        Thread t3 = new Thread("t3"){
            @Override
            public void run() {
                // t3是timed_waiting状态（有时限的等待）
                synchronized (SixState.class) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t4 = new Thread("t4"){
            @Override
            public void run() {
                // t4是waiting状态（无时限的等待）
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread t5 = new Thread("t5"){
            // t5调用start()方法，最后进入terminated状态
            @Override
            public void run() {

            }
        };

        Thread t6 = new Thread("t6"){
            @Override
            public void run() {
                // t6和t3共用同一把锁，t3拿到了锁，t6拿不到，t6就会进入blocked状态
                synchronized (SixState.class) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        TimeUnit.SECONDS.sleep(1);
        t2.start();
        t3.start();

        t4.start();
        t5.start();
        t6.start();
        log.debug("t1的状态：{}", t1.getState());
        log.debug("t2的状态：{}", t2.getState());
        log.debug("t3的状态：{}", t3.getState());
        log.debug("t4的状态：{}", t4.getState());
        log.debug("t5的状态：{}", t5.getState());
        log.debug("t6的状态：{}", t6.getState());

    }

}
