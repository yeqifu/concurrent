package com.yeqifu.test4.threadeightlock.test3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 落亦-
 * @Date: 2022/1/26 14:55
 */
@Slf4j
public class Test3 {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            n1.a();
        }).start();

        new Thread(() -> {
            n1.b();
        }).start();

        new Thread(() -> {
            n1.c();
        }).start();

        // 输出：1. 3，2，等一秒，1
        //      2. 3，等一秒，1，2
        //      4. 2，3，等一秒，1
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

    public void c() {
        log.debug("3");
    }
}
