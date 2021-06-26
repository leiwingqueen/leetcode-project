package com.liyongquan.bfs;

import java.util.*;

/**
 * 773. 滑动谜题
 * <p>
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * <p>
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * <p>
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * <p>
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * <p>
 * 示例：
 * <p>
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 * 提示：
 * <p>
 * board 是一个如上所述的 2 x 3 的数组.
 * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SlidingPuzzle {
    public static final int ROW = 2;
    public static final int COL = 3;

    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * bfs，查找最短路径
     *
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        Puzzle target = new Puzzle(new int[][]{
                {1, 2, 3},
                {4, 5, 0},
        });
        Puzzle start = new Puzzle(board);
        if (start.equals(target)) {
            return 0;
        }
        Queue<Puzzle> queue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        queue.add(start);
        depthQueue.add(0);
        Set<Puzzle> visit = new HashSet<>();
        visit.add(start);
        while (!queue.isEmpty()) {
            Puzzle poll = queue.poll();
            Integer depth = depthQueue.poll();
            List<Puzzle> next = poll.next();
            for (Puzzle puzzle : next) {
                if (puzzle.equals(target)) {
                    return depth + 1;
                }
                if (!visit.contains(puzzle)) {
                    visit.add(puzzle);
                    queue.add(puzzle);
                    depthQueue.add(depth + 1);
                }
            }
        }
        return -1;
    }

    private static class Puzzle implements Cloneable {
        int[][] board;

        public Puzzle(int[][] board) {
            this.board = board;
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (int[] b : board) {
                for (int i : b) {
                    sb.append(i + "#");
                }
            }
            return sb.toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Puzzle)) {
                return false;
            }
            Puzzle puzzle = (Puzzle) obj;
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j] != puzzle.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        protected Puzzle clone() {
            int[][] matrix = new int[ROW][COL];
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    matrix[i][j] = board[i][j];
                }
            }
            return new Puzzle(matrix);
        }

        public List<Puzzle> next() {
            int[] pos = new int[2];
            //找到0的位置
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j] == 0) {
                        pos[0] = i;
                        pos[1] = j;
                        break;
                    }
                }
            }
            List<Puzzle> res = new ArrayList<>(3);
            for (int[] dir : DIR) {
                int x = pos[0] + dir[0], y = pos[1] + dir[1];
                if (x >= 0 && x < ROW && y >= 0 && y < COL) {
                    Puzzle clone = this.clone();
                    //交换两个值
                    clone.board[pos[0]][pos[1]] = clone.board[x][y];
                    clone.board[x][y] = 0;
                    res.add(clone);
                }
            }
            return res;
        }
    }
}
