package com.yeqifu.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试啊
 * @Author: 落亦-
 * @Date: 2022/1/15 21:11
 */
@Slf4j
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建futureTask任务对象
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("FutrueTask-running");
                Thread.sleep(2000);
                return 100;
            }
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        log.debug("{}", futureTask.get());

    }
}
