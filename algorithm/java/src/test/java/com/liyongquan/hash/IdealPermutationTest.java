package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class IdealPermutationTest {
    private IdealPermutation idealPermutation = new IdealPermutation();

    @Test
    public void isIdealPermutation() {
        boolean res = this.idealPermutation.isIdealPermutation(new int[]{1, 0, 2});
        Assert.assertEquals(true, res);
    }
}