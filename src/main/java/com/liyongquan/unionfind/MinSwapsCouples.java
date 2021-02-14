package com.liyongquan.unionfind;

/**
 * 765. 情侣牵手
 * <p>
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 * <p>
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * <p>
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 示例 2:
 * <p>
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 说明:
 * <p>
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSwapsCouples {
    /**
     * 并查集
     * 还是很难想，看题解写出来，还是少了一点味道
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int len = row.length / 2;
        //合并连通分量
        UnionFind uf = new UnionFind(len);
        for (int i = 0; i < len; i++) {
            int x = row[2 * i] / 2;
            int y = row[2 * i + 1] / 2;
            if (x != y) {
                uf.union(x, y);
            }
        }
        //对每个连通分量分别统计节点的数量，其实总数就是连通分量去掉代表元的数量(每个连通分量的节点数量-1相加)
        //相当于节点数量减去连通分量的数量
        return len - uf.getCount();
    }

    /**
     * 并查集模板
     */
    private static class UnionFind {
        private int[] parent;

        private int count;

        public UnionFind(int len) {
            this.parent = new int[len];
            this.count = len;
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < len; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (parent[x] != x) {
                //路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                this.count -= 1;
                return true;
            } else {
                return false;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
