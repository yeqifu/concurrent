package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @Author: 落亦-
 * @Date: 2022/1/21 21:43
 */
@Slf4j
public class JoinTest {
    static int r = 0;
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    private static void test1() throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.debug("结束");
            r = 10;
        },"t1");
        t1.start();
        // 方法一：让主线程睡眠的时间比t1线程的时间还长
/*        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 方法二：调用join()方法，让t1线程执行完毕，再输出r
        t1.join();
        log.debug("结果为:{}", r);
        log.debug("结束");
    }
}
