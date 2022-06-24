package com.liyongquan.weeklycontest.bwc68;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class AbbreviateProductTest {
    private AbbreviateProduct ap = new AbbreviateProduct();

    //44
    //9556
    @Test
    public void abbreviateProduct() {
        String res = ap.abbreviateProduct(44, 9556);
        Assert.assertEquals("10205...06688e2378", res);
    }
}