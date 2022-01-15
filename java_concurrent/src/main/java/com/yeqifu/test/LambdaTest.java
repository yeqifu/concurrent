package com.yeqifu.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/15 20:51
 */
@Slf4j
public class LambdaTest {
    public static void main(String[] args) {

        // 创建任务对象
        Runnable task = () -> { log.debug("lambda-running"); };

        // 创建线程对象
        Thread thread = new Thread(task,"lambdaThread");

        thread.start();
    }
}
