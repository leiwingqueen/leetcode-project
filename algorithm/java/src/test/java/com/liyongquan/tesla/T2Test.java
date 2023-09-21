package com.liyongquan.tesla;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class T2Test {
    private T2 t2 = new T2();

    // "03-2021""04-2021""05-2021 B =03-2021""05-2021""05-2021" andC(20. 10, 15]
    @Test
    public void solution2() {
        int res = t2.solution2(new String[]{"03-2021", "04-2021", "05-2021"}, new String[]{"03-2021", "05-2021", "05-2021"}, new int[]{20, 10, 15});
        Assert.assertEquals(20, res);
    }

    // . Given A ="10-2020""01-2020""02-2020""06-20217 B = 07-2021""03-2020" "10-2020""07-2021"7 and C = l 10 2. 901 the function should return 13
    @Test
    public void t() {
        int res = t2.solution2(new String[]{"10-2020", "01-2020", "02-2020", "06-2021"}, new String[]{"07-2021", "03-2020", "10-2020", "07-2021"}, new int[]{1, 10, 2, 90});
        Assert.assertEquals(13, res);
    }


    // 3 Given A =01-1900""12-2099" "11-2099" 01-1901" B 12-1901" "12-2099" "12-2100""01-1902"] and C = [1, 1000, 998, 1], the function should return 7.
    @Test
    public void t3() {
        int res = t2.solution2(new String[]{"01-1900", "12-2099", "11-2099", "01-1901"}, new String[]{"12-1901", "12-2099", "12-2100", "01-1902"}, new int[]{1, 1000, 998, 1});
        Assert.assertEquals(7, res);
    }
}