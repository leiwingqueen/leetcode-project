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
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {

    }

    //线段树
    private static class Segment {
        int left;
        int right;
        int sum;
        Segment lNode;
        Segment rNode;

        public Segment(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }

        public static Segment build(int l, int r) {
            return new Segment(l, r, 0);
        }

        public void add(int l, int r) {
            this.sum++;
            int mid = left + (right - left) / 2;
            if (r <= mid) {
                if (lNode != null) {
                    lNode.add(l, r);
                }
            }
        }

        public void inc(int key) {
            this.sum++;
            if (this.left == this.right) {
                return;
            }
            int mid = left + (right - left) / 2;
            if (key <= mid) {
                if (lNode == null) {
                    lNode = new Segment(left, mid, 0);
                }
                lNode.inc(key);
            } else {
                if (rNode == null) {
                    rNode = new Segment(mid + 1, right, 0);
                }
                rNode.inc(key);
            }
        }

        //排序>=idx的最小key
        public int find(int idx) {
            if (sum < idx) {
                return -1;
            }
            if (left == right) {
                return left;
            }
            if (lNode != null && lNode.sum >= idx) {
                return lNode.find(idx);
            } else {
                return rNode.find(idx - lNode.sum);
            }
        }
    }
}
