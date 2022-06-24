package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/11
 */
@Slf4j
public class NumberToWordsTest {
    private NumberToWords ntw = new NumberToWords();

    @Test
    public void numberToWords() {
        String res = ntw.numberToWords(1000);
        Assert.assertEquals("One Thousand", res);
    }
}