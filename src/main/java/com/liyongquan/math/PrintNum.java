package com.liyongquan.math;

import java.util.Arrays;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintNum {
    public int[] printNumbers(int n) {
        int length=(int) Math.pow(10,n)-1;
        int[] result=new int[length];
        for (int i = 0; i < length; i++) {
            result[i]=i+1;
        }
        return result;
    }

    public static void main(String[] args) {
        PrintNum num=new PrintNum();
        int[] ints = num.printNumbers(2);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+",");
        }
        System.out.println();
    }
}
