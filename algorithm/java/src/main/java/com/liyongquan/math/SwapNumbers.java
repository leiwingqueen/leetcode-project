package com.liyongquan.math;

//编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
//
//示例：
//
//输入: numbers = [1,2]
//输出: [2,1]
//提示：
//
//numbers.length == 2
//-2147483647 <= numbers[i] <= 2147483647
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/swap-numbers-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/12/6
 */
public class SwapNumbers {
    /**
     * 计算差的操作
     *
     * @param numbers
     * @return
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[1] = numbers[1] - numbers[0];
        numbers[0] = numbers[1] + numbers[0];
        numbers[1] = numbers[0] - numbers[1];
        return numbers;
    }

    /**
     * 异或操作
     *
     * @param numbers
     * @return
     */
    public int[] swapNumbers2(int[] numbers) {
        numbers[1] = numbers[1] ^ numbers[0];
        numbers[0] = numbers[1] ^ numbers[0];
        numbers[1] = numbers[0] ^ numbers[1];
        return numbers;
    }
}
