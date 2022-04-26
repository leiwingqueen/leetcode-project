package com.liyongquan.weeklycontest.wc290;

//给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。同时给你一个下标从 0 开始大小为 n 的整数数组 persons ，persons[i] 是第 i 个人来看花的时间。
//
//请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
//
//
//
//示例 1：
//
//
//
//输入：flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
//输出：[1,2,2,2]
//解释：上图展示了每朵花的花期时间，和每个人的到达时间。
//对每个人，我们返回他们到达时在花期内花的数目。
//示例 2：
//
//
//
//输入：flowers = [[1,10],[3,3]], persons = [3,3,2]
//输出：[2,2,1]
//解释：上图展示了每朵花的花期时间，和每个人的到达时间。
//对每个人，我们返回他们到达时在花期内花的数目。
//
//
//提示：
//
//1 <= flowers.length <= 5 * 104
//flowers[i].length == 2
//1 <= starti <= endi <= 109
//1 <= persons.length <= 5 * 104
//1 <= persons[i] <= 109

public class FullBloomFlowers {
    /**
     * 线段树解法
     * <p>
     * 还是超时
     *
     * @param flowers
     * @param persons
     * @return
     */
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int mx = 1_000_000_000;
        Segment segment = new Segment(1, mx, 0);
        for (int[] flower : flowers) {
            segment.add(flower[0], flower[1], 1);
        }
        int[] res = new int[persons.length];
        for (int i = 0; i < persons.length; i++) {
            res[i] = segment.find(persons[i]);
        }
        return res;
    }

    //线段树
    private static class Segment {
        int left;
        int right;
        int sum;
        Segment lNode;
        Segment rNode;
        //懒更新标记
        int lazy;

        public Segment(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
            this.lazy = 0;
        }

        public static Segment build(int l, int r) {
            return new Segment(l, r, 0);
        }

        public void add(int l, int r, int val) {
            l = Math.max(l, left);
            r = Math.min(r, right);
            if (r < l) {
                return;
            }
            if (l != left || r != right) {
                //不能完全吻合，扩展下一层节点
                int mid = left + (right - left) / 2;
                if (lNode == null) {
                    lNode = new Segment(left, mid, sum);
                }
                if (rNode == null) {
                    rNode = new Segment(mid + 1, right, sum);
                }
                lNode.add(l, r, val + lazy);
                rNode.add(l, r, val + lazy);
                lazy = 0;
            } else {
                //完全吻合，需要的时候再更新到下层
                lazy += val;
            }
            this.sum += val;
        }

        /**
         * 更新下推
         */
        private void pushDown() {
            if (lazy == 0 || (lNode == null && rNode == null)) {
                return;
            }
            lNode.lazy += this.lazy;
            rNode.lazy += this.lazy;
            this.lazy = 0;
            lNode.pushDown();
            rNode.pushDown();
        }

        public int find(int idx) {
            //叶子节点
            if (lNode == null && rNode == null) {
                return sum;
            }
            int mid = left + (right - left) / 2;
            //更新子节点
            if (this.lazy > 0) {
                lNode.sum += this.lazy;
                lNode.lazy += this.lazy;
                rNode.sum += this.lazy;
                rNode.lazy += this.lazy;
                this.lazy = 0;
            }
            if (idx <= mid) {
                return lNode.find(idx);
            } else {
                return rNode.find(idx);
            }
        }
    }
}
