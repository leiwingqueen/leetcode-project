package com.liyongquan.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsAnagramTest {
    private IsAnagram ia = new IsAnagram();

    @Test
    public void isAnagram2() {
        boolean b = ia.isAnagram2("我是中国人", "人是我国中");
        System.out.println(b);
    }
}