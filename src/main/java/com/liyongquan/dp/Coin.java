package com.liyongquan.dp;

/**
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 *
 * 示例2:
 *
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 *
 * 说明：
 *
 * 注意:
 *
 * 你可以假设：
 *
 *     0 <= n (总金额) <= 1000000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Coin {
    /**
     * f(n)=f(n-25)+f(n-10)+f(n-5)+f(n-1);
     * f(0)=0;
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        int[] r=new int[n+1];
        for (int i = 0; i < n+1; i++) {
            if (i==0) {
                r[i]=0;
            }else {
                r[i]=get(r,i-25)+get(r,i-10)+get(r,i-5)+get(r,i-1);
            }
        }
        return r[n];
    }

    private int get(int[] r,int i){
        if(i<0){
            return 0;
        }
        return r[i];
    }

    public static void main(String[] args) {
        Coin coin=new Coin();
        int i = coin.waysToChange(6);
        System.out.println(i);
    }
}
