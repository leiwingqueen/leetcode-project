package com.liyongquan.fsm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountValidWordsTest {
    private CountValidWords cvw = new CountValidWords();

    @Test
    public void countValidWords() {
        int r = cvw.countValidWords("-");
        Assert.assertEquals(0, r);
    }
}