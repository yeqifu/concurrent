package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/21 10:59
 */
@Slf4j
public class SleepTest {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("t1 running");
            }
        };

        t1.start();

        // t1的状态为RUNNABLE
        log.debug("t1的status: {}",t1.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 因为主线程休眠了500毫秒，这时t1线程已经进入了run()方法，t1线程也休眠了2000毫秒，然后再输出t1的状态，则t1的状态为TIMED_WAITING
        log.debug("t1的status: {}",t1.getState());

    }
}
