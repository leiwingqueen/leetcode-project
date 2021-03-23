package com.liyongquan.design;

import java.util.*;

/**
 * 破冰游戏-代码优化
 * 1.更通用的解法
 * 2.修复上个版本的bug
 */
public class PenguinTrap3 {

    public static final Hex[] DIR = {
            new Hex(+1, -1, 0), new Hex(+1, 0, -1),
            new Hex(0, +1, -1), new Hex(-1, +1, 0),
            new Hex(-1, 0, +1), new Hex(0, -1, +1),
    };

    public Map<Hex, BlockType> next(Map<Hex, BlockType> map) {
        if (map.size() == 0) {
            return map;
        }
        Map<Hex, Block> blockMap = new HashMap<>();
        Queue<Hex> queue = new LinkedList<>();
        //访问列表
        Set<Hex> visit = new HashSet<>();
        //bfs初始化，把墙壁放进队列
        for (Map.Entry<Hex, BlockType> entry : map.entrySet()) {
            if (BlockType.WALL == entry.getValue()) {
                queue.add(entry.getKey());
                visit.add(entry.getKey());
            }
            blockMap.put(entry.getKey(), new Block(entry.getValue()));
        }
        //bfs
        while (!queue.isEmpty()) {
            Hex hex = queue.poll();
            //6个方向更新
            for (int i = 0; i < DIR.length; i++) {
                Hex dir = DIR[i];
                Hex h = hex.clone();
                h.move(dir);
                //剪枝，假如当前block已经入队，则不需要重复访问
                while (map.containsKey(h) && BlockType.BLOCK == map.get(h) && !visit.contains(h)) {
                    Block block = blockMap.get(h);
                    block.edge[i] = true;
                    //稳定的砖块继续放入队列中迭代
                    if (block.exist()) {
                        visit.add(h.clone());
                        queue.add(h.clone());
                    }
                    h.move(dir);
                }
            }
        }
        //输出结果
        Map<Hex, BlockType> res = new HashMap<>();
        for (Map.Entry<Hex, BlockType> entry : map.entrySet()) {
            if (BlockType.WALL == entry.getValue() || BlockType.EMPTY == entry.getValue()) {
                res.put(entry.getKey(), entry.getValue());
            } else {
                BlockType blockType = blockMap.get(entry.getKey()).exist() ? BlockType.BLOCK : BlockType.EMPTY;
                res.put(entry.getKey(), blockType);
            }
        }
        return res;
    }
}

enum BlockType {
    //墙壁、冰块、空
    WALL,
    BLOCK,
    EMPTY,
}

class Block {
    //对应6条边
    boolean edge[];
    BlockType type;

    public Block() {
        this(BlockType.BLOCK);
    }

    public Block(BlockType blockType) {
        this.edge = new boolean[6];
        this.type = blockType;
        if (BlockType.WALL == type) {
            Arrays.fill(this.edge, true);
        }
    }

    public boolean exist() {
        if (BlockType.WALL == type) {
            return true;
        }
        if (BlockType.EMPTY == type) {
            return false;
        }
        //检查对角边是否挨着墙壁
        for (int i = 0; i < 3; i++) {
            if (edge[i] && edge[i + 3]) {
                return true;
            }
        }
        //3角的形状
        for (int i = 0; i < 2; i++) {
            if (edge[i] && edge[i + 2] && edge[i + 4]) {
                return true;
            }
        }
        return false;
    }
}
