package com.yeqifu.test3;

/**
 * @Author: 落亦-
 * @Date: 2022/1/20 20:40
 */
public class FramesTest {
    public static void main(String[] args) {
        new Thread(() -> {
           method1(20);
        },"t1").start();

        method1(10);
    }

    private static void method1(int x) {
        int y = x + 1;
        Object o = method2();
        System.out.println(o);
    }

    private static Object method2() {
        Object o = new Object();
        return o;
    }
}
