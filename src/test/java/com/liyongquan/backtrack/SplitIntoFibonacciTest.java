package com.liyongquan.backtrack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class SplitIntoFibonacciTest {
    private SplitIntoFibonacci si = new SplitIntoFibonacci();

    @Test
    public void splitIntoFibonacci() {
        List<Integer> list = si.splitIntoFibonacci("123456579");
        list.stream().forEach((x) -> System.out.println(x));
    }

    @Test
    public void backtrack() {
        Deque<Integer> deque = new LinkedList();
        deque.add(123);
        List<Integer> list = si.backtrack(deque, "123456579", 3);
        list.stream().forEach(x -> System.out.println(x));
    }

    /**
     * "1320581321313221264343965566089105744171833277577"
     */
    @Test
    public void test2() {
        List<Integer> list = si.splitIntoFibonacci("1320581321313221264343965566089105744171833277577");
        list.stream().forEach(x -> System.out.println(x));
    }
}