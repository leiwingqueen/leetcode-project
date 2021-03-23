package com.liyongquan.design;

import java.util.*;

/**
 * <p>
 * 我们假设之前的状态是稳定的，那么是否有办法只更新掉落的冰块
 * <p>
 * bfs?
 */
public class PenguinTrap4 {
    public static final Hex[] DIR = {
            new Hex(+1, -1, 0), new Hex(+1, 0, -1),
            new Hex(0, +1, -1), new Hex(-1, +1, 0),
            new Hex(-1, 0, +1), new Hex(0, -1, +1),
    };

    /**
     * 敲击方法
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * <p>
     * bfs
     *
     * @param map  地图
     * @param drop 本次敲击掉落的冰块
     * @return 实际掉落的冰块
     */
    public List<Hex> knock(Map<Hex, BlockType> map, List<Hex> drop) {
        if (drop.size() == 0) {
            return Collections.emptyList();
        }
        List<Hex> res = new LinkedList<>();
        res.addAll(drop);
        Queue<Hex> queue = new LinkedList<>();
        //初始化
        for (Hex hex : drop) {
            queue.add(hex);
            map.put(hex, BlockType.EMPTY);
        }
        while (!queue.isEmpty()) {
            Hex poll = queue.poll();
            for (Hex dir : DIR) {
                Hex move = poll.clone().move(dir);
                if (map.containsKey(move) && map.get(move) == BlockType.BLOCK) {
                    if (!stable(map, move)) {
                        map.put(move, BlockType.EMPTY);
                        res.add(move);
                        queue.add(move);
                    }
                }
            }
        }
        return res;
    }

    private boolean stable(Map<Hex, BlockType> map, Hex hex) {
        //对边判断
        for (int i = 0; i < 3; i++) {
            Hex h1 = hex.clone().move(DIR[i]);
            Hex h2 = hex.clone().move(DIR[i + 3]);
            if (map.containsKey(h1) && map.get(h1) != BlockType.EMPTY
                    && map.containsKey(h2) && map.get(h2) != BlockType.EMPTY) {
                return true;
            }
        }
        //三角判断
        for (int i = 0; i < 2; i++) {
            Hex h1 = hex.clone().move(DIR[i]);
            Hex h2 = hex.clone().move(DIR[i + 2]);
            Hex h3 = hex.clone().move(DIR[i + 4]);
            if (map.containsKey(h1) && map.get(h1) != BlockType.EMPTY
                    && map.containsKey(h2) && map.get(h2) != BlockType.EMPTY
                    && map.containsKey(h3) && map.get(h3) != BlockType.EMPTY) {
                return true;
            }
        }
        return false;
    }
}
