package com.liyongquan.dp;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BestLineTest {
    private BestLine bl = new BestLine();

    /**
     * 输入： [[0,0],[1,1],[1,0],[2,0]]
     * 输出： [0,2]
     */
    @Test
    public void bestLine() {
        int[][] line = new int[][]{
                {0, 0},
                {1, 1},
                {1, 0},
                {2, 0}
        };
        int[] ints = bl.bestLine2(line);
        for (int i : ints) {
            System.out.print(i + ",");
        }
        Assert.assertArrayEquals(new int[]{0, 2}, ints);
    }

    //[[-38935,27124],[-39837,19604],[-7086,42194],[-11571,-23257],[115,-23257],[20229,5976],[24653,-18488],[11017,21043],[-9353,16550],[-47076,15237],[-36686,42194],[-17704,1104],[31067,7368],[-20882,42194],[-19107,-10597],[-14898,24506],[-20801,42194],[-52268,40727],[-14042,42194],[-23254,42194],[-30837,-53882],[1402,801],[-33961,-984],[-6411,42194],[-12210,22901],[-8213,-19441],[-26939,20810],[30656,-23257],[-27195,21649],[-33780,2717],[23617,27018],[12266,3608]]
    @Test
    public void getLine() {
        BigDecimal[] line = bl.getLine(new int[]{-7086, 42194}, new int[]{-36686, 42194});
        System.out.println(line[0]);
        System.out.println(line[0] + "," + line[1]);

        line = bl.getLine(new int[]{-7086, 42194}, new int[]{-6411, 42194});
        System.out.println(line[0] + "," + line[1]);
    }
}