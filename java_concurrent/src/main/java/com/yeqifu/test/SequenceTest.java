package com.yeqifu.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/20 19:35
 */
@Slf4j
public class SequenceTest {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.debug("戒骄戒躁，稳扎稳打！");
            }
        },"t1").start();

        new Thread(() -> {
            while (true) {
                log.debug("积累！");
            }
        },"t2").start();

    }
}
