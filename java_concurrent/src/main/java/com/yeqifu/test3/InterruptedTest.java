package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 打断阻塞状态下的线程
 *
 * @Author: 落亦-
 * @Date: 2022/1/22 11:19
 */
@Slf4j
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        log.debug("t1线程的打断状态：{}", t1.isInterrupted());
    }
}
