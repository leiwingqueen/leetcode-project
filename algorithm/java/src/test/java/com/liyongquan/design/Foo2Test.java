package com.liyongquan.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class Foo2Test {

    @Test
    public void test() {
        Foo2 foo = new Foo2();
        Thread t1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}