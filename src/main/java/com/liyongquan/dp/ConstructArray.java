package com.liyongquan.dp;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 *
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 *
 *
 * 提示：
 *
 *     所有元素乘积之和不会溢出 32 位整数
 *     a.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConstructArray {
    public int[] constructArr(int[] a) {
        int[][] result=new int[a.length][2];
        /**
         * 上半三角
         */
        int f0=1;
        for (int i = a.length-1; i >=0 ; i--) {
            result[i][0]=f0;
            f0*=a[i];
        }
        /**
         * 下半三角
         */
        f0=1;
        for (int i = 0; i < a.length; i++) {
            result[i][1]=f0;
            f0*=a[i];
        }
        int[] r=new int[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i]=result[i][0]*result[i][1];
        }
        return r;
    }

    public static void main(String[] args) {
        ConstructArray array=new ConstructArray();
        int[] ints = array.constructArr(new int[]{1, 2, 3, 4, 5});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
