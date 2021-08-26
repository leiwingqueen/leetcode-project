package com.liyongquan.dp;

//881. 救生艇
//第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
//
//每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
//
//返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
//
// 
//
//示例 1：
//
//输入：people = [1,2], limit = 3
//输出：1
//解释：1 艘船载 (1, 2)
//示例 2：
//
//输入：people = [3,2,2,1], limit = 3
//输出：3
//解释：3 艘船分别载 (1, 2), (2) 和 (3)
//示例 3：
//
//输入：people = [3,5,3,4], limit = 5
//输出：4
//解释：4 艘船分别载 (3), (3), (4), (5)
//提示：
//
//1 <= people.length <= 50000
//1 <= people[i] <= limit <= 30000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/boats-to-save-people
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/8/26
 */
public class NumRescueBoats {
    /**
     * 贪心解法
     * <p>
     * 每次从船只中选择剩余空间最大的，能放下则开船离开。否则申请一条新的船只
     * <p>
     * 时间复杂度O(nlogn)
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int cnt = 0;
        for (int i = people.length - 1; i >= 0; i--) {
            if (pq.isEmpty() || pq.peek() < people[i]) {
                cnt++;
                int left = limit - people[i];
                if (left > 0) {
                    pq.add(left);
                }
            } else {
                pq.poll();
            }
        }
        return cnt;
    }

    /**
     * 双指针
     * <p>
     * 每艘船分别尝试装最重和最轻的两个人
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0, r = people.length - 1;
        int cnt = 0;
        while (l <= r) {
            if (l == r) {
                cnt++;
                break;
            } else {
                int left = limit - people[r];
                cnt++;
                r--;
                if (left >= people[l]) {
                    l++;
                }
            }
        }
        return cnt;
    }
}
