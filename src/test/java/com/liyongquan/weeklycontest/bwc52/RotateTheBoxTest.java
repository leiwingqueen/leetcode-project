package com.liyongquan.weeklycontest.bwc52;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RotateTheBoxTest {
    private RotateTheBox rtb = new RotateTheBox();

    @Test
    public void rotateTheBox() {
        char[][] box = {{'#', '.', '#'}};
        char[][] res = rtb.rotateTheBox(box);
        for (char[] re : res) {
            StringBuilder sb = new StringBuilder();
            for (char c : re) {
                sb.append(c);
                sb.append(',');
            }
            log.info("{}", sb.toString());
        }
    }
}