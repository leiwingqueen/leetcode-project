package com.liyongquan.weeklycontest.bwc68;

//给你两个正整数 left 和 right ，满足 left <= right 。请你计算 闭区间 [left, right] 中所有整数的 乘积 。
//
//由于乘积可能非常大，你需要将它按照以下步骤 缩写 ：
//
//统计乘积中 后缀 0 的数目，并 移除 这些 0 ，将这个数目记为 C 。
//比方说，1000 中有 3 个后缀 0 ，546 中没有后缀 0 。
//将乘积中剩余数字的位数记为 d 。如果 d > 10 ，那么将乘积表示为 <pre>...<suf> 的形式，其中 <pre> 表示乘积最 开始 的 5 个数位，<suf> 表示删除后缀 0 之后 结尾的 5 个数位。如果 d <= 10 ，我们不对它做修改。
//比方说，我们将 1234567654321 表示为 12345...54321 ，但是 1234567 仍然表示为 1234567 。
//最后，将乘积表示为 字符串 "<pre>...<suf>eC" 。
//比方说，12345678987600000 被表示为 "12345...89876e5" 。
//请你返回一个字符串，表示 闭区间 [left, right] 中所有整数 乘积 的 缩写 。
//
// 
//
//示例 1：
//
//输入：left = 1, right = 4
//输出："24e0"
//解释：
//乘积为 1 × 2 × 3 × 4 = 24 。
//由于没有后缀 0 ，所以 24 保持不变，缩写的结尾为 "e0" 。
//因为乘积的结果是 2 位数，小于 10 ，所欲我们不进一步将它缩写。
//所以，最终将乘积表示为 "24e0" 。
//示例 2：
//
//输入：left = 2, right = 11
//输出："399168e2"
//解释：乘积为 39916800 。
//有 2 个后缀 0 ，删除后得到 399168 。缩写的结尾为 "e2" 。
//删除后缀 0 后是 6 位数，不需要进一步缩写。
//所以，最终将乘积表示为 "399168e2" 。
//示例 3：
//
//输入：left = 371, right = 375
//输出："7219856259e3"
//解释：乘积为 7219856259000 。
// 
//
//提示：
//
//1 <= left <= right <= 104
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/abbreviating-the-product-of-a-range
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class AbbreviateProduct {
    public String abbreviateProduct(int left, int right) {
        //m,n分别是2和5的质因子
        int m = 0, n = 0;
        //后5位
        long suf = 1;
        //前5位
        double p = 0;
        long mul = 1;
        for (int i = left; i <= right; i++) {
            //10的尾数
            int num = i;
            while (num > 0 && num % 2 == 0) {
                m++;
                num /= 2;
            }
            while (num > 0 && num % 5 == 0) {
                n++;
                num /= 5;
            }
            suf *= num;
            if (suf >= 100_000) {
                suf %= 100_000;
            }
            p += Math.log10(i);
            //这里可能会溢出
            mul *= num;
        }
        int c = Math.min(m, n);
        p -= c;
        //前面可能多除了2和5，这里要乘回去
        for (int i = 0; i < m - c; i++) {
            suf *= 2;
            mul *= 2;
            if (suf >= 100_000) {
                suf %= 100_000;
            }
        }
        for (int i = 0; i < n - c; i++) {
            suf *= 5;
            mul *= 5;
            if (suf >= 100_000) {
                suf %= 100_000;
            }
        }
        //10位以内
        if (p <= 10) {
            return String.format("%de%d", mul, c);
        } else {
            long pre = (long) Math.pow(10D, p - Math.floor(p) + 4);
            return String.format("%d...%05de%d", pre, suf, c);
        }
    }
}
