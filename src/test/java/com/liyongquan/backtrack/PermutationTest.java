package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PermutationTest {
    private Permutation permutation = new Permutation();

    @Test
    public void permutation() {
        String[] res = permutation.permutation("abc");
        for (String s : res) {
            log.info("{}", s);
        }
    }
}