package com.liyongquan.dp;

import org.junit.Test;

import static org.junit.Assert.*;

public class VideoStitchingTest {
    private VideoStitching vs = new VideoStitching();

    @Test
    public void videoStitching() {
        int[][] clips = new int[][]{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int result = vs.videoStitching(clips, 10);
        System.out.println(result);
    }
}