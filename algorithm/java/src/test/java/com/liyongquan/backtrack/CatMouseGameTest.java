package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CatMouseGameTest {
    private CatMouseGame game = new CatMouseGame();

    @Test
    public void catMouseGame() {
        int[][] graph = {
                {2, 5},
                {3},
                {0, 4, 5},
                {1, 4, 5},
                {2, 3},
                {0, 2, 3}
        };
        int r = game.catMouseGame(graph);
        Assert.assertEquals(0, r);
    }
}