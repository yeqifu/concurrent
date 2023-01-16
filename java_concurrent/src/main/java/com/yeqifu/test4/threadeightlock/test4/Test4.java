package com.yeqifu.test4.threadeightlock.test4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 落亦-
 * @Date: 2022/1/26 15:14
 */
public class Test4 {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(() -> {
            try {
                n1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            n2.b();
        }, "t2").start();
    }

    // 输出：2,等一秒，1
}

@Slf4j
class Number {
    public synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}
