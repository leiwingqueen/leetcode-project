package com.liyongquan.hash;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindCommonCharactersTest {
    private FindCommonCharacters fc = new FindCommonCharacters();

    @Test
    public void commonChars() {
        List<String> result = fc.commonChars(new String[]{"bella", "label", "roller"});
        Assert.assertEquals(3, result.size());
    }
}