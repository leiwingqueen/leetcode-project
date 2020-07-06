package com.liyongquan.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: True
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [0,0,1,2,5]
 * 输出: True
 *  
 * <p>
 * 限制：
 * <p>
 * 数组长度为 5 
 * <p>
 * 数组的数取值为 [0, 13] .
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Shunzi {
    public boolean isStraight(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list);
        int joker = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                joker++;
            } else {
                if (i > 0 && nums[i - 1] != 0) {
                    if (nums[i] == nums[i - 1] + 1) {
                        continue;
                    } else if (nums[i] == nums[i - 1]) {
                        return false;
                    } else if (joker > 0) {
                        int padding = nums[i] - nums[i - 1] - 1;
                        if (padding > joker) {
                            return false;
                        }
                        joker -= padding;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}


