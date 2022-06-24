package com.liyongquan.unionfind;

import java.util.*;

/**
 * 冗余连接2
 * <p>
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 * <p>
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * <p>
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 * <p>
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的有向图如下:
 * 1
 * / \
 * v   v
 * 2-->3
 * 示例 2:
 * <p>
 * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * 输出: [4,1]
 * 解释: 给定的有向图如下:
 * 5 <- 1 -> 2
 * ^    |
 * |    v
 * 4 <- 3
 * 注意:
 * <p>
 * 二维数组大小的在3到1000范围内。
 * 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RedundantDirectedConnection {
    /**
     * 树：一个节点只有一个父节点。在构成树的时候我们需要有公共父节点的边放到最后
     *
     * 不通过，还需要修改
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        UnionFind uf = new UnionFind(len);
        Map<Integer, EdgeList> map = new HashMap<>();
        //多个结果，优先输出靠后的边，因为尝试倒序遍历
        for (int i = len - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (map.containsKey(edge[1])) {
                map.get(edge[1]).add(edge);
            } else {
                map.put(edge[1], new EdgeList(edge[1], edge));
            }
        }
        List<EdgeList> list = new ArrayList<>(map.size());
        for (EdgeList value : map.values()) {
            list.add(value);
        }
        //把终点有重复的边放在最后添加
        Collections.sort(list, Comparator.comparingInt(o -> o.edges.size()));
        for (EdgeList edge : list) {
            for (int[] e : edge.edges) {
                if (uf.count == 1) {
                    return e;
                }
                boolean union = uf.union(e[0] - 1, e[1] - 1);
                if (!union) {
                    return e;
                }
            }
        }
        //实际不会走到这个逻辑
        return new int[]{};
    }

    private static class EdgeList {
        int endPoint;
        List<int[]> edges;

        public EdgeList(int endPoint, int[] edge) {
            this.endPoint = endPoint;
            this.edges = new ArrayList<>();
            this.edges.add(edge);
        }

        public void add(int[] edge) {
            this.edges.add(edge);
        }
    }

    /**
     * 并查集模板
     */
    private static class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int size) {
            this.parent = new int[size];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                count++;
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
                count--;
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
