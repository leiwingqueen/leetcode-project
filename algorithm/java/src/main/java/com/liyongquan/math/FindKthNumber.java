package com.liyongquan.math;

//440. 字典序的第K小数字
//给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
//
// 
//
//示例 1:
//
//输入: n = 13, k = 2
//输出: 10
//解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
//示例 2:
//
//输入: n = 1, k = 1
//输出: 1
// 
//
//提示:
//
//1 <= k <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class FindKthNumber {
    /**
     * 这道题真心有点难，看了题解才勉强写出来
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        return find(1, n, k);
    }

    private int find(int cur, int n, int k) {
        if (k == 1) {
            return cur;
        }
        int step = count(cur, cur, n);
        if (k > step) {
            k -= step;
            //从右边的兄弟节点开始找
            return find(cur + 1, n, k);
        } else {
            //从子节点开始找
            k -= 1;
            return find(cur * 10, n, k);
        }
    }

    private int count(int first, int last, int n) {
        //当前这一层的数量
        int cnt = last - first + 1;
        if ((long) first * 10 > n) {
            return cnt;
        }
        //计算下一层的数量
        int nf = first * 10;
        int nl = (int) Math.min((long) last * 10 + 9, n);
        return cnt + count(nf, nl, n);
    }
}
