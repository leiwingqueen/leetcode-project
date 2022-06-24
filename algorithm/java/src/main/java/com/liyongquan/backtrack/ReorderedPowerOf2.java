package com.liyongquan.backtrack;

import java.util.ArrayList;
import java.util.List;

//重排列得到2的幂
//给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
//
//如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
//
// 
//
//示例 1：
//
//输入：1
//输出：true
//示例 2：
//
//输入：10
//输出：false
//示例 3：
//
//输入：16
//输出：true
//示例 4：
//
//输入：24
//输出：false
//示例 5：
//
//输入：46
//输出：true
// 
//
//提示：
//
//1 <= N <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reordered-power-of-2
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/10/28
 */
public class ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int[] arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        for (int i = 0; i < 31; i++) {
            if (backtrace(arr, 0, 1 << i)) {
                return true;
            }
        }
        return false;
    }

    private boolean backtrace(int[] nums, int idx, int target) {
        if (idx == nums.length) {
            return target == 0;
        }
        //前导0直接退出
        if (idx == nums.length - 1 && nums[idx] == 0) {
            return false;
        }
        if (target == 0) {
            return false;
        }
        int num = target % 10;
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] == num) {
                swap(nums, idx, i);
                if (backtrace(nums, idx + 1, target / 10)) {
                    return true;
                }
                swap(nums, i, idx);
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
