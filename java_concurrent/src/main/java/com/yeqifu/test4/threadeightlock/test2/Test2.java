package com.yeqifu.test4.threadeightlock.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 落亦-
 * @Date: 2022/1/26 14:38
 */
@Slf4j
public class Test2 {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(() -> {
            log.debug(Thread.currentThread().getName()+"begin");
            number.a();
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.debug(Thread.currentThread().getName()+"begin");
            number.b();
        }, "t2");

        t1.start();
        t2.start();

        // 输出结果为：2，等一秒1 或 等一秒输出1，再输出2
    }
}

@Slf4j
class Number {
    public synchronized void a() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}
