package com.liyongquan.unionfind;

//给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
//
//有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
//只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
//返回 图中最大连通组件的大小 。
//
// 
//
//示例 1：
//
//
//
//输入：nums = [4,6,15,35]
//输出：4
//示例 2：
//
//
//
//输入：nums = [20,50,9,63]
//输出：2
//示例 3：
//
//
//
//输入：nums = [2,3,6,7,4,12,21,39]
//输出：8
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//1 <= nums[i] <= 105
//nums 中所有值都 不同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-component-size-by-common-factor
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestComponentSize {
    private Map<Integer, List<Integer>> map;
    private int[] nums;

    public int largestComponentSize(int[] nums) {
        map = new HashMap<>();
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            split(i);
        }
        UnionFind uf = new UnionFind(nums.length);
        for (List<Integer> list : map.values()) {
            for (int i = 1; i < list.size(); i++) {
                uf.union(list.get(0), list.get(i));
            }
        }
        return uf.getCount();
    }

    private void split(int idx) {
        int tmp = nums[idx];
        for (int i = 2; i * i <= tmp; i++) {
            if (tmp % i == 0) {
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }
                map.get(i).add(idx);
            }
            while (tmp % i == 0) {
                tmp /= i;
            }
        }
    }

    /**
     * 并查集模板
     */
    private static class UnionFind {
        private int[] parent;
        private int[] cnt;

        public UnionFind(int size) {
            this.parent = new int[size];
            this.cnt = new int[size];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                cnt[i] = 1;
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

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                cnt[rootY] += cnt[rootX];
            }
        }

        public int getCount() {
            int mx = Integer.MIN_VALUE;
            for (int v : cnt) {
                if (v > mx) {
                    mx = v;
                }
            }
            return mx;
        }
    }
}
