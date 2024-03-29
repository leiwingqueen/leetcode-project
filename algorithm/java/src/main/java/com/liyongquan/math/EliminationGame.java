package com.liyongquan.math;

//390. 消除游戏
//列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
//
//从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
//重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
//不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
//给你整数 n ，返回 arr 最后剩下的数字。
//
// 
//
//示例 1：
//
//输入：n = 9
//输出：6
//解释：
//arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
//arr = [2, 4, 6, 8]
//arr = [2, 6]
//arr = [6]
//示例 2：
//
//输入：n = 1
//输出：1
// 
//
//提示：
//
//1 <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/elimination-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class EliminationGame {
    public int lastRemaining(int n) {
        int start = 1, step = 1;
        int k = 1;
        while (n > 1) {
            //奇数
            if (k % 2 == 1) {
                start = start + step;
            } else {
                //偶数
                if (n % 2 == 1) {
                    start = start + step;
                }
                //其他情况start不需要修改
            }
            step <<= 1;
            k++;
            n >>= 1;
        }
        return start;
    }
}
