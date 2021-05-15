package com.liyongquan.weeklycontest.bwc52;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SortSentenceTest {
    private SortSentence ss = new SortSentence();

    @Test
    public void sortSentence() {
        String res = ss.sortSentence("is2 sentence4 This1 a3");
        log.info("{}", res);
    }
}