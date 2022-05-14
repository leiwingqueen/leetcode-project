package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinStickersTest {
    private MinStickers minStickers = new MinStickers();

    @Test
    public void minStickers() {
        int res = minStickers.minStickers(new String[]{"with", "example", "science"},
                "thehat");
        Assert.assertEquals(3, res);
    }

    //["these","guess","about","garden","him"]
    //"atomher"
    @Test
    public void test() {
        int res = minStickers.minStickers(new String[]{"these", "guess", "about", "garden", "him"}, "atomher");
        Assert.assertEquals(3, res);
    }
}