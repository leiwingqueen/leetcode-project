package com.liyongquan.backtrack;

import com.liyongquan.weeklycontest.spring2021.PurchasePlans;

import java.util.LinkedList;
import java.util.List;

//282. 给表达式添加运算符
//给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
//
// 
//
//示例 1:
//
//输入: num = "123", target = 6
//输出: ["1+2+3", "1*2*3"]
//示例 2:
//
//输入: num = "232", target = 8
//输出: ["2*3+2", "2+3*2"]
//示例 3:
//
//输入: num = "105", target = 5
//输出: ["1*0+5","10-5"]
//示例 4:
//
//输入: num = "00", target = 0
//输出: ["0+0", "0-0", "0*0"]
//示例 5:
//
//输入: num = "3456237490", target = 9191
//输出: []
// 
//
//提示：
//
//1 <= num.length <= 10
//num 仅含数字
//-231 <= target <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/expression-add-operators
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/10/18
 */
public class AddOperators {
    private List<String> res = new LinkedList<>();
    private int target;

    public List<String> addOperators(String num, int target) {
        int len = num.length();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = num.charAt(i) - '0';
        }
        char[] exp = new char[2 * len - 1];
        this.target = target;
        backtrace(nums, exp, 0, 0, 0, 1, 0);
        return res;
    }

    /**
     * @param nums
     * @param exp
     * @param idx1
     * @param idx2    当前计算得到的结果总和
     * @param current 乘数
     * @param plusNum 紧接着的最后一个数字
     * @param lastNum
     */
    private void backtrace(int[] nums, char[] exp, int idx1, int idx2, long current, long plusNum, long lastNum) {
        if (idx1 == nums.length) {
            current += plusNum * lastNum;
            if (current == target) {
                res.add(new String(exp, 0, idx2));
            }
            return;
        }
        //不插运算符的场景，需要保证没有前导0
        if (idx2 == 0 || lastNum != 0 || exp[idx2 - 1] != '0') {
            exp[idx2] = (char) (nums[idx1] + '0');
            backtrace(nums, exp, idx1 + 1, idx2 + 1, current, plusNum, lastNum * 10 + nums[idx1]);
        }
        if (idx2 > 0 && exp[idx2 - 1] >= '0' && exp[idx2 - 1] <= '9') {
            //插入加法
            exp[idx2] = '+';
            backtrace(nums, exp, idx1, idx2 + 1, current + plusNum * lastNum, 1, 0);
            //减法
            exp[idx2] = '-';
            backtrace(nums, exp, idx1, idx2 + 1, current + plusNum * lastNum, -1, 0);
            //乘法
            exp[idx2] = '*';
            backtrace(nums, exp, idx1, idx2 + 1, current, plusNum * lastNum, 0);
        }
    }
}
