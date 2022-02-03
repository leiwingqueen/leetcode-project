package com.liyongquan.greedy;

//给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
//
//斐波那契数字定义为：
//
//F1 = 1
//F2 = 1
//Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
//数据保证对于给定的 k ，一定能找到可行解。
//
// 
//
//示例 1：
//
//输入：k = 7
//输出：2
//解释：斐波那契数字为：1，1，2，3，5，8，13，……
//对于 k = 7 ，我们可以得到 2 + 5 = 7 。
//示例 2：
//
//输入：k = 10
//输出：2
//解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
//示例 3：
//
//输入：k = 19
//输出：3
//解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
// 
//
//提示：
//
//1 <= k <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FindMinFibonacciNumbers {
    /**
     * 贪心的解法有时候是最难想的
     *
     * @param k
     * @return
     */
    public int findMinFibonacciNumbers(int k) {
        LinkedList<Integer> fib = new LinkedList<>();
        int f1 = 1, f2 = 1;
        fib.offerFirst(f1);
        fib.offerFirst(f2);
        while (f2 < k) {
            int f3 = f1 + f2;
            fib.offerFirst(f3);
            f1 = f2;
            f2 = f3;
        }
        int cnt = 0;
        while (k > 0) {
            for (Integer num : fib) {
                if (k >= num) {
                    cnt++;
                    k -= num;
                    break;
                }
            }
        }
        return cnt;
    }


    //预先计算
    public static final int[] FIB = {
            1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
            2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811,
            514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817,
            39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170
    };

    //由于斐波那契数列的单调性，我们可以用二分查找加速查找过程
    public int findMinFibonacciNumbers2(int k) {
        int cnt = 0;
        while (k > 0) {
            int num = search(FIB, k);
            k -= num;
            cnt++;
        }
        return cnt;
    }

    private int search(int[] fib, int k) {
        int l = 0, r = fib.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (fib[mid] <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return fib[l];
    }

    /**
     * 还有一个理解，我们下一次取的数不可能比上一次的数字大
     *
     * @param k
     * @return
     */
    public int findMinFibonacciNumbers3(int k) {
        int cnt = 0;
        int idx = FIB.length - 1;
        while (k > 0) {
            while (k < FIB[idx]) {
                idx--;
            }
            k -= FIB[idx];
            cnt++;
        }
        return cnt;
    }
}
