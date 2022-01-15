package com.yeqifu.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/15 20:36
 */
@Slf4j
public class RunnableTest {
    public static void main(String[] args) {

        // 创建任务对象
        Runnable task = new Runnable() {
            @Override
            public void run() {
                log.debug("runnable-running");
            }
        };

        // 创建线程对象，参数1：任务对象  参数2：线程名称
        Thread thread = new Thread(task,"threadTwo");
        thread.start();
    }
}
