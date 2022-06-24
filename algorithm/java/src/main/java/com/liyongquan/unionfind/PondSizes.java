package com.liyongquan.unionfind;

import java.util.*;

//你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指
//相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
// 示例：
// 输入：
//[
//  [0,2,1,0],
//  [0,1,0,1],
//  [1,1,0,1],
//  [0,1,0,1]
//]
//输出： [1,2,4]
//
// 提示：
//
// 0 < len(land) <= 1000
// 0 < len(land[i]) <= 1000
//
// Related Topics 深度优先搜索 广度优先搜索
// 👍 51 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class PondSizes {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
    };

    /**
     * 先来一个bfs
     *
     * @param land
     * @return
     */
    public int[] pondSizes(int[][] land) {
        if (land.length == 0) {
            return new int[]{};
        }
        int row = land.length, col = land[0].length;
        int[][] visit = new int[row][col];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visit[i][j] == 1 || land[i][j] != 0) {
                    continue;
                }
                Queue<int[]> queue = new LinkedList<>();
                visit[i][j] = 1;
                queue.add(new int[]{i, j});
                int cnt = 1;
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    for (int[] dir : DIR) {
                        int x = poll[0] + dir[0], y = poll[1] + dir[1];
                        if (x >= 0 & x < row && y >= 0 && y < col && visit[x][y] == 0 && land[x][y] == 0) {
                            visit[x][y] = 1;
                            queue.offer(new int[]{x, y});
                            cnt++;
                        }
                    }
                }
                res.add(cnt);
            }
        }
        if (res.size() == 0) {
            return new int[]{};
        }
        //排序输出
        Collections.sort(res);
        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

