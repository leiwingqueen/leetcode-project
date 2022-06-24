package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ClothTest {
    private Cloth cloth = new Cloth();

    @Test
    public void satisfy() {
        String[][] list = {
                {"S", "M"},
                {"XL", "2XL"},
                {"XS", "M"},
                {"XL", "2XL"},
                {"M", "L"},
                {"S", "M"},
                {"L", "XL"},
        };
        int res = cloth.satisfy(list);
        log.info("{}", res);
    }
}