package com.liyongquan.array;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.events.DTD;

import static org.junit.Assert.*;

/**
 * * 示例1:
 * * <p>
 * * 输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 * * 输出：[3]
 * * 说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
 * * 示例2:
 * * <p>
 * * 输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 * * 输出：[-1, -1, -1]
 */
public class DrawLineTest {
    private DrawLine dl = new DrawLine();

    @Test
    public void drawLine() {
        int[] r1 = dl.drawLine(1, 32, 30, 31, 0);
        Assert.assertArrayEquals(new int[]{3}, r1);
        int[] r2 = dl.drawLine(3, 96, 0, 95, 0);
        Assert.assertArrayEquals(new int[]{-1, -1, -1}, r2);
    }
}