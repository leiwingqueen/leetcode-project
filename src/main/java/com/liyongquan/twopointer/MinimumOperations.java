package com.liyongquan.twopointer;

/**
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 * <p>
 * 示例 1：
 * <p>
 * 输入：leaves = "rrryyyrryyyrr"
 * <p>
 * 输出：2
 * <p>
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 * <p>
 * 示例 2：
 * <p>
 * 输入：leaves = "ryr"
 * <p>
 * 输出：0
 * <p>
 * 解释：已符合要求，不需要额外操作
 * <p>
 * 提示：
 * <p>
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/UlBDOe
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumOperations {
    /**
     * 题目的数组长度为百万级别的，这意味着我们只能用线性/nlogn的复杂度来解决，首先想到的是双指针解法
     *
     * @param leaves
     * @return
     */
    public int minimumOperations(String leaves) {
        int l = 0, r = leaves.length() - 1;
        int count = 0;
        if (leaves.charAt(l) == 'y') {
            count++;
        }
        if (leaves.charAt(r) == 'y') {
            count++;
        }
        l++;
        r--;
        //找到左右边界第一个非r的元素
        while (l < r && leaves.charAt(l) == 'r') {
            l++;
        }
        if (l == r) {
            return leaves.charAt(l) == 'y' ? count : count + 1;
        }
        while (l < r && leaves.charAt(r) == 'r') {
            r--;
        }
        //统计l,r之间的r的数量
        for (int i = l + 1; i < r; i++) {
            if (leaves.charAt(i) == 'r') {
                count++;
            }
        }
        return count;
    }
}
