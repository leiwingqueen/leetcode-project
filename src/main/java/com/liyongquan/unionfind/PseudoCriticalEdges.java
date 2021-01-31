package com.liyongquan.unionfind;

import com.liyongquan.dfs.CircleNum;

import javax.swing.table.TableRowSorter;
import java.util.*;

/**
 * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 * <p>
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 * <p>
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * 输出：[[0,1],[2,3,4,5]]
 * 解释：上图描述了给定图。
 * 下图是所有的最小生成树。
 * <p>
 * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
 * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
 * 示例 2 ：
 * <p>
 * <p>
 * <p>
 * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * 输出：[[],[0,1,2,3]]
 * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti <= 1000
 * 所有 (fromi, toi) 数对都是互不相同的。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PseudoCriticalEdges {
    /**
     * MST的生成算法
     * <p>
     * 如果相同权值的边都能够联通不同的子集，那么边就是关键边，某则是非关键边
     * <p>
     * 证明：
     * 假设权值为k的边(定义为集合M)均连接了不同的子集，边e属于子集M，假设e是非关键边，则意味着不选e也能生成一个MST。
     * 但是不选e意味着在>k的边上需要多选一条边，那么最终的结果必然会比原来的结果大
     *
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int len = edges.length;
        List<Edge> edgeList = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            edgeList.add(new Edge(i, edges[i][0], edges[i][1], edges[i][2]));
        }
        //先对边进行排序
        Collections.sort(edgeList, Comparator.comparingInt(o -> o.weight));
        List<Integer> critical = new ArrayList<>(), pseudo = new ArrayList<>();
        UnionFind uf = new UnionFind(n);
        int l = 0, r = 0;
        while (l < len) {
            if (uf.count == 1) {
                break;
            }
            //找到相同权值边的右边界
            while (r < len && edgeList.get(l).weight == edgeList.get(r).weight) {
                r++;
            }
            //判断[l,r)是否都是关键边
            boolean res = true;
            List<Integer> idxList = new ArrayList<>(r - l);
            for (int i = l; i < r; i++) {
                Edge edge = edgeList.get(i);
                if (res && !uf.union(edge.from, edge.to)) {
                    res = false;
                }
                idxList.add(edge.idx);
            }
            if (res) {
                critical.addAll(idxList);
            } else {
                pseudo.addAll(idxList);
            }
            l = r;
        }
        //整合并返回结果
        List<List<Integer>> mergeRes = new ArrayList<>(2);
        mergeRes.add(critical);
        mergeRes.add(pseudo);
        return mergeRes;
    }

    private static class Edge {
        int idx;
        int from;
        int to;
        int weight;

        public Edge(int idx, int from, int to, int weight) {
            this.idx = idx;
            this.from = from;
            this.to = to;
            this.weight = weight;
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
