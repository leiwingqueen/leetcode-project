package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterReplacementTest {
    private CharacterReplacement cr = new CharacterReplacement();

    @Test
    public void characterReplacement() {
        int r = cr.characterReplacement("ABAB", 2);
        System.out.println(r);
        Assert.assertEquals(4, r);
    }

    @Test
    public void test2() {
        int r = cr.characterReplacement("AABABBA", 1);
        System.out.println(r);
    }

    @Test
    public void characterReplacement2() {
        int r = cr.characterReplacement2("ABBB", 2);
        System.out.println(r);
        Assert.assertEquals(4, r);
    }

}