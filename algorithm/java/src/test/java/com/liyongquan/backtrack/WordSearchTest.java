package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class WordSearchTest {
    private WordSearch ws = new WordSearch();

    /**
     * [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']]
     * 'ABCCED'
     */
    @Test
    public void exist() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        boolean res = ws.exist(board, "ABCCED");
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}