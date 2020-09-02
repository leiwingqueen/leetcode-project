package com.liyongquan.dp;


/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountBits {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度 O(n*sizeof(interger))
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] r = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            r[i] = cb(i);
        }
        return r;
    }

    private int cb(int num) {
        int c = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                c++;
            }
            num /= 2;
        }
        return c;
    }

    /**
     * 奇偶判断+dp
     * 奇数：
     * f(n)=f(n)+1
     * 偶数：
     * f(n)=f(n/2)
     *
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] r = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            //等价于取模
            if ((i&1)==1) {
                r[i]=r[i-1]+1;
            }else{
                //右移等价于/2
                r[i]=r[i>>1];
            }
        }
        return r;
    }
}
