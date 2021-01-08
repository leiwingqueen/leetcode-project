package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class AreSentencesSimilarTest {
    private AreSentencesSimilar as = new AreSentencesSimilar();

    /**
     * ["great","acting","skills"]
     * ["fine","drama","talent"]
     * [["great","fine"],["drama","acting"],["skills","talent"]]
     */
    @Test
    public void areSentencesSimilar() {
        List<List<String>> list = new LinkedList<>();
        list.add(Arrays.asList("great", "fine"));
        list.add(Arrays.asList("drama", "acting"));
        list.add(Arrays.asList("skills", "talent"));
        boolean b = as.areSentencesSimilar(new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"}, list);
        log.info("res:{}", b);
        Assert.assertEquals(true, b);
    }
}