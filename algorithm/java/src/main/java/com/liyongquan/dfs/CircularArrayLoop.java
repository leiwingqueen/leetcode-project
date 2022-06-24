package com.liyongquan.dfs;

//457. 环形数组是否存在循环
// 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
//
//如果 nums[i] 是正数，向前 移动 nums[i] 步
//如果 nums[i] 是负数，向后 移动 nums[i] 步
//因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
//
//数组中的 循环 由长度为 k 的下标序列 seq ：
//
//遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
//所有 nums[seq[j]] 应当不是 全正 就是 全负
//k > 1
//如果 nums 中存在循环，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：nums = [2,-1,1,2,2]
//输出：true
//解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
//示例 2：
//
//输入：nums = [-1,2]
//输出：false
//解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
//示例 3:
//
//输入：nums = [-2,1,-1,-2,-2]
//输出：false
//解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
//所有 nums[seq[j]] 应当不是全正就是全负。
// 
//
//提示：
//
//1 <= nums.length <= 5000
//-1000 <= nums[i] <= 1000
//nums[i] != 0
// 
//
//进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/circular-array-loop
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/8/7
 */
public class CircularArrayLoop {
    /**
     * dfs增加记忆来判断环
     * <p>
     * 假设一条路径上的下一个点和上一个点重叠，则我们可以认为存在环，并且路径上的所有点我们都可以标记成存在环
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        int[] loop = new int[len];
        for (int i = 0; i < len; i++) {
            if (dfs(nums, len, i, new int[len], loop, nums[i] > 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int[] nums, int len, int start, int[] path, int[] loop, boolean dir) {
        if (loop[start] == 1) {
            return true;
        }
        if (path[start] == 1) {
            loop[start] = 1;
            return true;
        }
        //同向保证
        if (!dir && nums[start] > 0 || dir && nums[start] < 0) {
            return false;
        }
        //负数处理
        int next = start + nums[start];
        while (next < 0) {
            next += len;
        }
        next %= len;
        //next==start的场景，认为不存在环
        if (next == start) {
            return false;
        }
        path[start] = 1;
        boolean r = dfs(nums, len, next, path, loop, dir);
        if (r) {
            loop[start] = 1;
        }
        path[start] = 0;
        return r;
    }

    /**
     * 快慢指针
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int slow = i;
            int fast = getNext(nums, i);
            if (fast == slow) {
                continue;
            }
            boolean dir = nums[i] > 0;
            while (slow != fast) {
                int next = getNext(nums, fast);
                if (next == fast) {
                    
                }
            }
        }
        return false;
    }

    private int getNext(int[] nums, int i) {
        int next = i + nums[i];
        while (next < 0) {
            next += nums.length;
        }
        return next % nums.length;
    }
}
