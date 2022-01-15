package com.yeqifu.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 落亦-
 * @Date: 2022/1/15 20:27
 */
@Slf4j(topic = "c.ThreadTest")
public class ThreadTest {
    public static void main(String[] args) {
        // 创建线程对象，构造方法的参数是给线程指定名字，推荐
        Thread thread = new Thread("threadOne"){
            // 要执行的任务
            @Override
            public void run() {
                log.debug("threadOne-running");
            }
        };
        //  启动线程
        thread.start();

        log.debug("main-running");
    }
}
