package com.liyongquan.weeklycontest.spring2021;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class EscapeMazeTest {
    private EscapeMaze em = new EscapeMaze();

    @Test
    public void escapeMaze() {
        String[][] maze = {
                {"...", "...", "..."},
                {".##", "###", "##."},
                {".##", "###", "##."},
                {".##", "###", "##."},
                {".##", "###", "##."},
                {".##", "###", "##."},
                {".##", "###", "##."}
        };
        List<List<String>> mazeList = new ArrayList<>(maze.length);
        for (String[] strings : maze) {
            mazeList.add(Arrays.asList(strings));
        }
        boolean res = em.escapeMaze(mazeList);
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }
}