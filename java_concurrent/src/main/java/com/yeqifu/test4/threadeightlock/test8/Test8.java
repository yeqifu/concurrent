package com.yeqifu.test4.threadeightlock.test8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 落亦-
 * @Date: 2022/1/26 15:40
 */
@Slf4j
public class Test8 {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(()->{
            log.debug(Thread.currentThread().getName()+"begin");
            try {
                n1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(()->{
            log.debug(Thread.currentThread().getName()+"begin");
            n2.b();
        }, "t2").start();

        // 输出：2，等一秒，1或等一秒，1,2
    }
}

@Slf4j
class Number{
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.debug("1");
    }
    public static synchronized void b() {
        log.debug("2");
    }
}