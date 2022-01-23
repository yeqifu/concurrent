package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 守护进程
 *
 * @Author: 落亦-
 * @Date: 2022/1/23 10:39
 */
@Slf4j
public class DaemonTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.debug("守护线程");
                    break;
                }
            }
        }, "t1");

        // 设置t1为守护线程
        t1.setDaemon(true);

        t1.start();

        // 并没有打断t1，而t1是while的死循环，所以t1应该是一直执行的，但是设置了t1为守护线程，所以当主线程执行完毕之后，t1会自动结束
        TimeUnit.SECONDS.sleep(1);

        log.debug("主线程结束");
    }

}
