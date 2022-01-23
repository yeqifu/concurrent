package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 统筹之烧水泡茶
 *
 * @Author: 落亦-
 * @Date: 2022/1/23 12:10
 */
@Slf4j
public class Overall {
    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(() -> {
            // t2线程进行洗水壶 1分钟、烧开水 15分钟
            log.debug("洗水壶 1分钟、烧开水 15分钟");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        // 声明一个洗茶壶、洗茶杯和拿茶叶的线程，需要4分钟
        Thread t1 = new Thread(() -> {
            log.debug("洗茶壶、洗茶杯和拿茶叶");
            try {
                // 洗茶壶、洗茶杯和拿茶叶需要4分钟
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 等待t2线程将水烧开再泡茶
            try {
                log.debug("等待t2线程将水烧开");
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("完成泡茶！");
        }, "t1");

        t1.start();
        t2.start();

    }

}
