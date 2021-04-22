package com.liyongquan.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PenguinTrap5 {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 1-冰块，0是空
     *
     * @param map
     * @param block
     * @return
     */
    public List<int[]> knock(int[][] map, int[] block) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(block);
        List<int[]> res = new ArrayList<>();
        res.add(block);
        map[block[0]][block[1]] = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] dir : DIR) {
                int x = poll[0] + dir[0], y = poll[1] + dir[1];
                int[] position = {x, y};
                if (!stable(map, position)) {
                    map[x][y] = 0;
                    queue.add(position);
                    res.add(position);
                }
            }
        }
        return res;
    }

    private boolean stable(int[][] map, int[] position) {
        int row = map.length, col = map[0].length;
        int x = position[0];
        int y = position[1];
        if (x < 0 || x > row || y < 0 || y > col || map[x][y] == 0) {
            return true;
        }
        int[] up = {x - 1, y}, down = {x + 1, y};
        if ((up[0] < 0 || map[up[0]][up[1]] == 1) && (down[0] >= row || map[down[0]][down[1]] == 1)) {
            return true;
        }
        //TODO:左右两边
        return false;
    }
}
