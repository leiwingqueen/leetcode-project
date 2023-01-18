package com.liyongquan.weeklycontest.wc236;

import java.util.*;

// 给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。
//
//MK 平均值 按照如下步骤计算：
//
//如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。
//从这个容器中删除最小的 k 个数和最大的 k 个数。
//计算剩余元素的平均值，并 向下取整到最近的整数 。
//请你实现 MKAverage 类：
//
//MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。
//void addElement(int num) 往数据流中插入一个新的元素 num 。
//int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。
// 
//
//示例 1：
//
//输入：
//["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
//[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
//输出：
//[null, null, null, -1, null, 3, null, null, null, 5]
//
//解释：
//MKAverage obj = new MKAverage(3, 1);
//obj.addElement(3);        // 当前元素为 [3]
//obj.addElement(1);        // 当前元素为 [3,1]
//obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
//obj.addElement(10);       // 当前元素为 [3,1,10]
//obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
//                          // 删除最小以及最大的 1 个元素后，容器为 [3]
//                          // [3] 的平均值等于 3/1 = 3 ，故返回 3
//obj.addElement(5);        // 当前元素为 [3,1,10,5]
//obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
//obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
//obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
//                          // 删除最小以及最大的 1 个元素后，容器为 [5]
//                          // [5] 的平均值等于 5/1 = 5 ，故返回 5
// 
//
//提示：
//
//3 <= m <= 105
//1 <= k*2 < m
//1 <= num <= 105
//addElement 与 calculateMKAverage 总操作次数不超过 105 次。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/finding-mk-average
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class MKAverage4 {
    private TMap s1;
    private TMap s2;
    private TMap s3;
    private Deque<Integer> queue;
    private int m;
    private int k;

    public MKAverage4(int m, int k) {
        this.s1 = new TMap();
        this.s2 = new TMap();
        this.s3 = new TMap();
        queue = new LinkedList<>();
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        queue.addLast(num);
        if (queue.size() < m) {
            s2.add(num, 1);
        } else if (queue.size() == m) {
            s2.add(num, 1);
            while (s1.getCnt() < k) {
                Map.Entry<Integer, Integer> first = s2.getMap().firstEntry();
                int move = Math.min(first.getValue(), k - s1.getCnt());
                s1.add(first.getKey(), move);
                s2.remove(first.getKey(), move);
            }
            while (s3.getCnt() < k) {
                Map.Entry<Integer, Integer> lastEntry = s2.getMap().lastEntry();
                int move = Math.min(lastEntry.getValue(), k - s3.getCnt());
                s3.add(lastEntry.getKey(), move);
                s2.remove(lastEntry.getKey(), move);
            }
        } else {
            if (num < s1.getMap().lastKey()) {
                Integer transferNum = s1.getMap().lastKey();
                s1.remove(transferNum, 1);
                s1.add(num, 1);
                s2.add(transferNum, 1);
            } else if (num > s3.getMap().lastKey()) {
                Integer transferNum = s3.getMap().firstKey();
                s3.remove(transferNum, 1);
                s3.add(num, 1);
                s2.add(transferNum, 1);
            } else {
                s2.add(num, 1);
            }
            // 移走左边的元素
            Integer removeNum = queue.pollFirst();
            if (s2.contains(removeNum)) {
                s2.remove(removeNum, 1);
            } else if (s1.contains(removeNum)) {
                s1.remove(removeNum, 1);
                Integer transfer = s2.getMap().firstKey();
                s2.remove(transfer, 1);
                s1.add(transfer, 1);
            } else {
                s3.remove(removeNum, 1);
                Integer transfer = s2.getMap().lastKey();
                s2.remove(transfer, 1);
                s3.add(transfer, 1);
            }
        }
    }


    public int calculateMKAverage() {
        if (queue.size() < this.m) {
            return -1;
        }
        return (int) (s2.sum / s2.cnt);
    }

    private static class TMap {
        private int cnt;
        private TreeMap<Integer, Integer> map;
        private long sum;

        public TMap() {
            map = new TreeMap<>();
        }

        public void add(int num, int cnt) {
            map.put(num, map.getOrDefault(num, 0) + cnt);
            this.cnt += cnt;
            this.sum += cnt * num;
        }

        public void remove(int num, int cnt) {
            map.put(num, map.get(num) - cnt);
            if (map.get(num) <= 0) {
                map.remove(num);
            }
            this.cnt -= cnt;
            this.sum -= cnt * num;
        }

        public boolean contains(int num) {
            return map.containsKey(num);
        }

        public int getCnt() {
            return cnt;
        }

        public TreeMap<Integer, Integer> getMap() {
            return map;
        }
    }
}
