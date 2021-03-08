package com.liyongquan.design;

import java.util.Arrays;

/**
 * 企鹅游戏
 * 使用Axial coordinates进行坐标存储
 * <p>
 * 参考：
 * https://www.redblobgames.com/grids/hexagons/
 * <p>
 * -1表示墙壁，0表示空砖块，1表示正常砖块
 */
public class PenguinTrap2 {
    /**
     * 1.从边缘开始分别针对6个方向做更新
     * 2.对于已经稳定的砖块，继续更新6个方向的砖块的边
     * <p>
     * 时间复杂度O(n^2)
     *
     * @param matrix
     * @return 更新后的砖块
     */
    public int[][] next(int[][] matrix) {
        if (matrix.length == 0) {
            return matrix;
        }
        int row = matrix.length, col = matrix[0].length;
        //初始化block
        Block[][] blocks = new Block[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                BlockType blockType;
                switch (matrix[i][j]) {
                    case -1:
                        blockType = BlockType.WALL;
                        break;
                    case 1:
                        blockType = BlockType.BLOCK;
                        break;
                    default:
                        blockType = BlockType.EMPTY;
                }
                blocks[i][j] = new Block(blockType);
            }
        }
        //左上和右下两个方向遍历
        for (int q = 0; q < col; q++) {
            for (int r = 0; r < row; r++) {
                if (BlockType.WALL == blocks[r][q].type || BlockType.EMPTY == blocks[r][q].type) {
                    continue;
                }
                if (r == 0 || blocks[r - 1][q].edge[0]) {
                    blocks[r][q].edge[0] = true;
                }
            }
        }
        for (int q = 0; q < col; q++) {
            for (int r = row - 1; r >= 0; r--) {
                if (BlockType.WALL == blocks[r][q].type || BlockType.EMPTY == blocks[r][q].type) {
                    continue;
                }
                if (r == row - 1 || blocks[r + 1][q].edge[3]) {
                    blocks[r][q].edge[3] = true;
                }
            }
        }
        //左右两个方向扫描
        for (int r = 0; r < row; r++) {
            for (int q = 0; q < col; q++) {
                if (BlockType.WALL == blocks[r][q].type || BlockType.EMPTY == blocks[r][q].type) {
                    continue;
                }
                if (q == 0 || blocks[r][q - 1].edge[5]) {
                    blocks[r][q].edge[5] = true;
                }
            }
        }
        for (int r = 0; r < row; r++) {
            for (int q = col - 1; q >= 0; q--) {
                if (BlockType.WALL == blocks[r][q].type || BlockType.EMPTY == blocks[r][q].type) {
                    continue;
                }
                if (q == col - 1 || blocks[r][q + 1].edge[2]) {
                    blocks[r][q].edge[2] = true;
                }
            }
        }
        //右上和左下两个方向扫描(相对复杂一丢丢)
        for (int i = 0; i < row + col - 1; i++) {
            int q = Math.min(col - 1, i);
            int r = i < col ? 0 : i - col + 1;
            while (r < row && q >= 0) {
                if (BlockType.BLOCK == blocks[r][q].type) {
                    if (r == 0 || q == col - 1 || blocks[r - 1][q + 1].edge[1]) {
                        blocks[r][q].edge[1] = true;
                    }
                }
                r++;
                q--;
            }
        }
        for (int i = 0; i < row + col - 1; i++) {
            int r = i < row ? i : 0;
            int q = i < row ? 0 : i - row + 1;
            while (r >= 0 && q < col) {
                if (BlockType.BLOCK == blocks[r][q].type) {
                    if (r == row - 1 || q == 0 || blocks[r + 1][q - 1].edge[4]) {
                        blocks[r][q].edge[4] = true;
                    }
                }
                r--;
                q++;
            }
        }
        //针对已经稳定的砖块做bfs，继续更新边
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bfs(blocks, new int[]{i, j}, row, col);
            }
        }
        //生成结果
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (BlockType.WALL == blocks[i][j].type) {
                    res[i][j] = -1;
                } else if (BlockType.BLOCK == blocks[i][j].type && blocks[i][j].exist()) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }

    //周围的6个格子
    public static final int[][] DIR = {
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
    };


    private void bfs(Block[][] blocks, int[] start, int row, int col) {
        if (blocks[start[0]][start[1]].type != BlockType.BLOCK || !blocks[start[0]][start[1]].exist()) {
            return;
        }
        //遍历6个方向更新
        //左上和右下
        for (int i = 0; i < DIR.length; i++) {
            int[] dir = DIR[i];
            int x = start[0], y = start[1];
            while (x >= 0 && x < row && y >= 0 && y < col && blocks[x][y].type == BlockType.BLOCK) {
                blocks[x][y].edge[i] = true;
                x += dir[0];
                y += dir[1];
            }
        }
    }

    private enum BlockType {
        //墙壁、砖块、空
        WALL,
        BLOCK,
        EMPTY,
    }

    private static class Block {
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
}
