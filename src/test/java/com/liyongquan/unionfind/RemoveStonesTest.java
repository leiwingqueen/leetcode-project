package com.liyongquan.unionfind;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveStonesTest {
    private RemoveStones rs = new RemoveStones();

    @Test
    public void removeStones() {
        new int[][]{
                {0,0},
                {0,1},
                {1,0},[1,2],[2,1],[2,2]};
        rs.removeStones()
    }
}