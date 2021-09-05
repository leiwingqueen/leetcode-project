package com.liyongquan.math;

import java.util.concurrent.ThreadLocalRandom;

//470. 用 Rand7() 实现 Rand10()
//已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
//
//不要使用系统的 Math.random() 方法。
//
// 
//
//示例 1:
//
//输入: 1
//输出: [7]
//示例 2:
//
//输入: 2
//输出: [8,4]
//示例 3:
//
//输入: 3
//输出: [8,1,10]
// 
//
//提示:
//
//rand7 已定义。
//传入参数: n 表示 rand10 的调用次数。
// 
//
//进阶:
//
//rand7()调用次数的 期望值 是多少 ?
//你能否尽量少调用 rand7() ?
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/5
 */
public class Rand10 {
    public int rand10() {
        int idx = 49;
        while (idx >= 40) {
            int row = rand7();
            int col = rand7();
            idx = (row - 1) * 7 + col - 1;
        }
        return idx % 10 + 1;
    }

    private int rand7() {
        return ThreadLocalRandom.current().nextInt(7) + 1;
    }
}
