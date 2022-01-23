package com.yeqifu.test4;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/23 16:06
 */
@Slf4j
public class ResolveQuestion {

    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (ResolveQuestion.class) {
                        count++;
                    }
                }
            }
        };

        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (ResolveQuestion.class) {
                        count--;
                    }
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",count);
    }

}
