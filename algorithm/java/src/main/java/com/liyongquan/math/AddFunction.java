package com.liyongquan.math;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 *
 *
 * 提示：
 *
 *     a, b 均可能是负数或 0
 *     结果不会溢出 32 位整数
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddFunction {
    /**
     * 题目为easy，实际上不容易，特别是对于需要找到位运算的规律
     *
     * 对于不进位的数字，使用异或运算进行计算
     * c1=a^b
     * 对于进位的位，使用&和左移一位来解决
     * c2=(a&b)<<1
     *
     * a+b=c1+c2
     *
     * 但这里有一点，最终运算还是使用到了加法，结果又陷入了套娃
     * 这里我们可以注意到的是，随着c2的不断左移，最终一定会变成0
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b!=0){
            int temp=a^b;
            b=(a&b)<<1;
            a=temp;
        }
        return a;
    }

    public static void main(String[] args) {
        AddFunction addFunction=new AddFunction();
        int add = addFunction.add(-2, 2);
        System.out.println(add);
    }
}
