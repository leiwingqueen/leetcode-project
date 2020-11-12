package com.liyongquan.array;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PermutationSequenceTest {
    private PermutationSequence ps = new PermutationSequence();

    @Test
    public void permute() {
        String s = ps.getPermutation(3, 2);
        System.out.println(s);
    }

    @Test
    public void getPermutation2() {
        String s = ps.getPermutation2(3, 2);
        System.out.println(s);
    }
}