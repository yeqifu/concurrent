package com.yeqifu.test4.threadeightlock.test1;

import lombok.extern.slf4j.Slf4j;


/**
 * 线程八锁
 *
 * @Author: 落亦-
 * @Date: 2022/1/26 12:01
 */
@Slf4j
public class Test1 {

    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(() -> {
            number.a();
        }, "t1");

        Thread t2 = new Thread(() -> {
            number.b();
        }, "t2");

        t1.start();
        t2.start();
    }
}

@Slf4j
class Number {
    public synchronized void a() {
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}

