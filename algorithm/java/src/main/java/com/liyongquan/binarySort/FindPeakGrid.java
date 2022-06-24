package com.liyongquan.binarySort;

//1901. 找出顶峰元素 II
//一个 2D 网格中的 顶峰元素 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
//
//给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 顶峰元素 mat[i][j] 并 返回其位置 [i,j] 。
//
//你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
//
//要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
//
// 
//
// 
//
//示例 1:
//
//
//
//输入: mat = [[1,4],[3,2]]
//输出: [0,1]
//解释: 3和4都是顶峰元素，所以[1,0]和[0,1]都是可接受的答案。
//示例 2:
//
//
//
//输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
//输出: [1,1]
//解释: 30和32都是顶峰元素，所以[1,1]和[2,2]都是可接受的答案。
// 
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 500
//1 <= mat[i][j] <= 105
//任意两个相邻元素均不相等.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-a-peak-element-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class FindPeakGrid {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * 暴力解法
     * <p>
     * 时间复杂度O(m*n)
     *
     * @param mat
     * @return
     */
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for (int[] dir : DIRS) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] >= mat[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    //TODO:二分查找，其实这个思路不好想
    public int[] findPeakGrid2(int[][] mat) {
        int m = mat.length;
        int l = 0, r = m - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int[] before = new int[]{-1, 0};
            int[] after = new int[]{-1, 0};
            if (mid - 1 >= 0) {
                before = findMax(mat, mid - 1);
            }
            if (mid + 1 < r) {
                after = findMax(mat, mid + 1);
            }
            int[] current = findMax(mat, mid);
            if (current[0] >= before[0] && current[0] >= after[0]) {
                //相邻格子不同，所以这里这么写也是严谨的
                return new int[]{mid, current[1]};
            } else if (current[0] < before[0]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    private int[] findMax(int[][] mat, int row) {
        int n = mat[0].length;
        int max = -1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (mat[row][i] > max) {
                max = mat[row][i];
                idx = i;
            }
        }
        return new int[]{max, idx};
    }
}
