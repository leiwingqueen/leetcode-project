package com.liyongquan.weeklycontest.spring2021;

import java.util.PriorityQueue;

/**
 * LCP 30. 魔塔游戏
 * <p>
 * 小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 * <p>
 * 小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]
 * <p>
 * 输出：1
 * <p>
 * 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-200,-300,400,0]
 * <p>
 * 输出：-1
 * <p>
 * 解释：调整访问顺序也无法完成全部房间的访问。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/p0NxJO
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MagicTower {
    /**
     * 不通过
     *
     * @param nums
     * @return
     */
    public int magicTower(int[] nums) {
        int len = nums.length;
        int blood = 1;
        int last = 0, cnt = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= 0 || blood + nums[i] > 0) {
                blood += nums[i];
            } else {
                last += nums[i];
                cnt++;
            }
        }
        return blood + last <= 0 ? -1 : cnt;
    }

    /**
     * 求左边的最小值
     *
     * @param nums
     * @return
     */
    public int magicTower2(int[] nums) {
        int len = nums.length;
        long blood = 1;
        long last = 0;
        int cnt = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            pq.add((long) nums[i]);
            blood += nums[i];
            if (blood <= 0) {
                if (pq.isEmpty() || pq.peek() >= 0) {
                    return -1;
                }
                Long min = pq.poll();
                last += min;
                blood -= min;
                cnt++;
            }
        }
        return blood + last <= 0 ? -1 : cnt;
    }
}
