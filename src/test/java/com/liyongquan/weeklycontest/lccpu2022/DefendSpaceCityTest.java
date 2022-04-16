package com.liyongquan.weeklycontest.lccpu2022;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class DefendSpaceCityTest {
    private DefendSpaceCity city = new DefendSpaceCity();

    /**
     * time = [1,1,1,2,2,3,5], position = [1,2,3,1,2,1,3]
     */
    @Test
    public void defendSpaceCity() {
        int res = city.defendSpaceCity(new int[]{1, 1, 1, 2, 2, 3, 5}, new int[]{1, 2, 3, 1, 2, 1, 3});
        Assert.assertEquals(9, res);
    }
}