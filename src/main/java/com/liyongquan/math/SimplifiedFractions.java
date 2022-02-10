package com.liyongquan.math;

import java.util.LinkedList;
import java.util.List;

//给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
//
// 
//
//示例 1：
//
//输入：n = 2
//输出：["1/2"]
//解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
//示例 2：
//
//输入：n = 3
//输出：["1/2","1/3","2/3"]
//示例 3：
//
//输入：n = 4
//输出：["1/2","1/3","1/4","2/3","3/4"]
//解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
//示例 4：
//
//输入：n = 1
//输出：[]
// 
//
//提示：
//
//1 <= n <= 100
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/simplified-fractions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class SimplifiedFractions {
    /**
     * 穷举？
     *
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        List<String> res = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (check(i, j)) {
                    res.add(j + "/" + i);
                }
            }
        }
        return res;
    }

    //判断是否有约数
    private boolean check(int a, int b) {
        for (int i = 2; i <= a; i++) {
            if (a % i == 0 && b % i == 0) {
                return false;
            }
        }
        return true;
    }
}
