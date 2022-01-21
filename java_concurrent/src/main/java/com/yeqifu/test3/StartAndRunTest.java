package com.yeqifu.test3;

import lombok.extern.slf4j.Slf4j;



/**
 * @Author: 落亦-
 * @Date: 2022/1/21 10:09
 */
@Slf4j
public class StartAndRunTest {
    public static void main(String[] args) {
        Thread thread = new Thread("t1"){
            @Override
            public void run() {
                log.debug("t1 running...");

            }
        };

        thread.start();
        log.debug("do other things...");
    }
}
