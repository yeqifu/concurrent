package com.yeqifu.test4.threadeightlock.test6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 落亦-
 * @Date: 2022/1/26 15:28
 */
@Slf4j
public class Test6 {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            log.debug(Thread.currentThread().getName()+"begin");
            n1.a();
        },"t1").start();

        new Thread(() -> {
            log.debug(Thread.currentThread().getName()+"begin");
            n1.b();
        }, "t2").start();

        // 输出：2，等一秒，1或等一秒，1,2
    }
}

@Slf4j
class Number {
    public static synchronized void a() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("1");
    }

    public static synchronized void b() {
        log.debug("2");
    }
}