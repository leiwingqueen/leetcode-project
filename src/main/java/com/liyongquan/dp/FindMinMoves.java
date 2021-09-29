package com.liyongquan.dp;

//517. 超级洗衣机
//假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
//
//在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
//
//给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
//
// 
//
//示例 1：
//
//输入：machines = [1,0,5]
//输出：3
//解释：
//第一步:    1     0 <-- 5    =>    1     1     4
//第二步:    1 <-- 1 <-- 4    =>    2     1     3
//第三步:    2     1 <-- 3    =>    2     2     2
//示例 2：
//
//输入：machines = [0,3,0]
//输出：2
//解释：
//第一步:    0 <-- 3     0    =>    1     2     0
//第二步:    1     2 --> 0    =>    1     1     1
//示例 3：
//
//输入：machines = [0,2,0]
//输出：-1
//解释：
//不可能让所有三个洗衣机同时剩下相同数量的衣物。
// 
//
//提示：
//
//n == machines.length
//1 <= n <= 104
//0 <= machines[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/super-washing-machines
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/9/29
 */
public class FindMinMoves {
    /**
     * 贪心
     *
     * @param machines
     * @return
     */
    public int findMinMoves(int[] machines) {
        int len = machines.length;
        if (len == 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += machines[i];
        }
        if (sum % len != 0) {
            return -1;
        }
        int avg = sum / len;
        //[下标，衣服数量]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < len; i++) {
            if (machines[i] > avg) {
                pq.add(new int[]{i, machines[i]});
            }
        }
        int res = 0;
        while (pq.size() > 0) {
            int[] machine = pq.poll();
            int[] visit = new int[len];
            visit[machine[0]] = 1;
            //从两边延展，发现小于avg的衣服增加
            int l = machine[0] - 1, r = machine[0] + 1;
            while (true) {
                if (l >= 0 && machine[l] < avg) {

                }
            }
        }
        return res;
    }
}
