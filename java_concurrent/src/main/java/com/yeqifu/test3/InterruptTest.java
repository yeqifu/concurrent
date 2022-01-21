package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/21 11:19
 */
@Slf4j
public class InterruptTest {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("enter sleep...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up");
                    e.printStackTrace();
                }
            }
        };

        t1.start();

        // 先运行t1线程，t1线程睡眠2000毫秒，主线程休眠1000毫秒后，打断t1线程的睡眠，t1线程睡眠被打断抛出异常
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打断t1线程的睡眠
        log.debug("interrupt");
        t1.interrupt();

    }
}
