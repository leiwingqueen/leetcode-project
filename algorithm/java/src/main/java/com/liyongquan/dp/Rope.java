package com.liyongquan.dp;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。
 * 请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的
 * 最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rope {
    /**
     * 假设F(n,m)为n长度的分成m段的乘积
     * 则f(n)=max{F(n,m)}。其中1<m<n
     *
     * dp方程:F(n,m)=max{F(n-1,m-1)*1,F(n-2,m-1)*2,...F(1,m-1)*(n-1)}
     *
     * 边界值:
     * if n<m : F(n,m)=0
     * if n==m : F(n,m)=1
     * if m=1 :F(n,m)=n
     *
     * 由上面的特性，我们对dp方程还可以做进一步的简化:
     * F(n,m)=max{F(n-1,m-1)*1,F(n-2,m-1)*2,...F(m-1,m-1)*(n-m+1)}
     *
     * 假如我们把中间结果构造成一个n*m的二维数组，则数组中轴线右上方的结点都是没必要计算的。
     *
     * 复杂度约为O(n(n-1)/2*(n/2))。比O(n^3)稍好点
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        //为了简化下标的理解，只使用1~n
        if (n<=2) {
            return 1;
        }
        int[][] middleResult=new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j==1) {
                    middleResult[i][j]=i;
                }else if(i==j){
                    middleResult[i][j]=1;
                }else {
                    int max=0;
                    for (int k = i-1; k >=j-1 ; k--) {
                        int r = middleResult[k][j - 1] * (i-k);
                        if (r>max) {
                            max=r;
                        }
                    }
                    middleResult[i][j]=max;
                }
            }
        }
        int result=0;
        for (int i = 2; i <= n; i++) {
            if (middleResult[n][i]>result) {
                result=middleResult[n][i];
            }
        }
        //print(middleResult);
        return result;
    }

    public static void main(String[] args) {
        Rope rope=new Rope();
        int i = rope.cuttingRope(10);
        System.out.println(i);
    }

    private void print(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]+",");
            }
            System.out.println();
        }
    }
}
