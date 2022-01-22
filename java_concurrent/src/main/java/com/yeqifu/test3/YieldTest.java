package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;


/**
 * @Author: 落亦-
 * @Date: 2022/1/21 12:17
 */
@Slf4j
public class YieldTest {

    public static void main(String[] args) {
        Runnable taskOne = () -> {
            int count = 0;
            for ( ; ; ) {
                System.out.println("------------>"+count++);
            }
        };

        Runnable taskTwo = () -> {
            int count = 0;
            for ( ; ; ) {
                Thread.yield();
                System.out.println("              ------------>"+count++);
            }
        };

        Thread thread = new Thread(taskOne);

        Thread threadTwo = new Thread(taskTwo);
        thread.start();
        threadTwo.start();

    }
}