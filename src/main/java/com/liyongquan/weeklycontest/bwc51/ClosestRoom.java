package com.liyongquan.weeklycontest.bwc51;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestRoom {
    /**
     * 暴力解法
     *
     * @param rooms
     * @param queries
     * @return
     */
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        Arrays.sort(rooms, Comparator.comparingInt(o -> o[0]));
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int id = -1;
            int distance = Integer.MAX_VALUE;
            for (int[] room : rooms) {
                if (room[1] >= query[1] && Math.abs(room[0] - query[0]) < distance) {
                    distance = Math.abs(room[0] - query[0]);
                    id = room[0];
                }
            }
            res[i] = id;
        }
        return res;
    }

    public int[] closestRoom2(int[][] rooms, int[][] queries) {
        Arrays.sort(rooms, (o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int id = -1;
            int distance = Integer.MAX_VALUE;
            for (int[] room : rooms) {
                if (room[1] >= query[1] && Math.abs(room[0] - query[0]) < distance) {
                    distance = Math.abs(room[0] - query[0]);
                    id = room[0];
                }
            }
            res[i] = id;
        }
        return res;
    }
}
