package com.liyongquan.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * 示例 2：
 * <p>
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * 示例 3：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：["-1"]
 * 示例 5：
 * <p>
 * 输入：nums = [0]
 * 输出：["0"]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SummaryRanges {
    /**
     * 同向双指针
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        List<String> res = new LinkedList<>();
        int l = 0, r = 0;
        //用<=主要为了正确处理最后一个元素
        while (r <= nums.length) {
            if (r == nums.length || nums[r] != nums[l] + r - l) {
                //l,r-1加入结果
                if (r - 1 == l) {
                    res.add(String.valueOf(nums[l]));
                } else {
                    res.add(nums[l] + "->" + nums[r - 1]);
                }
                l = r;
            }
            r++;
        }
        return res;
    }
}
