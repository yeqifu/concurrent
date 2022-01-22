package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;

/**
 * 打断正常运行的线程
 *
 * @Author: 落亦-
 * @Date: 2022/1/22 12:29
 */
@Slf4j
public class InterruptedTwoTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean isInterrupted = Thread.currentThread().isInterrupted();
                if (isInterrupted) {
                    log.debug("线程t1被打断了，退出循环");
                    break;
                }
            }
        }, "t1");

        t1.start();
        log.debug("interrupt");
        t1.interrupt();

    }
}
