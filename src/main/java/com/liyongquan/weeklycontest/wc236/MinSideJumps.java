package com.liyongquan.weeklycontest.wc236;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinSideJumps {
    public int minSideJumps(int[] obstacles) {
        int len = obstacles.length;
        long f1 = obstacles[1] != 1 ? 1 : Integer.MAX_VALUE;
        long f2 = obstacles[1] != 2 ? 0 : Integer.MAX_VALUE;
        long f3 = obstacles[1] != 3 ? 1 : Integer.MAX_VALUE;
        for (int i = 2; i < len; i++) {
            long nf1 = obstacles[i] == 1 ? Integer.MAX_VALUE : Math.min(f1,
                    Math.min(obstacles[i] == 2 ? Integer.MAX_VALUE : f2, obstacles[i] == 3 ? Integer.MAX_VALUE : f3) + 1);
            long nf2 = obstacles[i] == 2 ? Integer.MAX_VALUE : Math.min(f2,
                    Math.min(obstacles[i] == 1 ? Integer.MAX_VALUE : f1, obstacles[i] == 3 ? Integer.MAX_VALUE : f3) + 1);
            long nf3 = obstacles[i] == 3 ? Integer.MAX_VALUE : Math.min(f3,
                    Math.min(obstacles[i] == 1 ? Integer.MAX_VALUE : f1, obstacles[i] == 2 ? Integer.MAX_VALUE : f2) + 1);
            f1 = nf1;
            f2 = nf2;
            f3 = nf3;
            //log.info("{},{},{}", f1, f2, f3);
        }
        return (int) Math.min(f1, Math.min(f2, f3));
    }

}
