package com.liyongquan.dp;


/**
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/one-away-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OneEditAway {
    /**
     * 贪心？
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        boolean change = false;
        int i = 0, j = 0;
        while (i < first.length()) {
            if (j < second.length()) {
                if (first.charAt(i) == second.charAt(j)) {
                    i++;
                    j++;
                    continue;
                }
                if (change) {
                    return false;
                }
                //3种场景
                if (first.length() == second.length()) {
                    return eq(first, second, i + 1, j + 1);
                } else if (first.length() < second.length()) {
                    return eq(first, second, i, j + 1);
                } else {
                    return eq(first, second, i + 1, j);
                }
            } else {
                if (change) {
                    return false;
                }
                //这里相当于删除操作
                change = true;
                i++;
            }
        }
        return true;
    }

    private boolean eq(String s1, String s2, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) {
            return true;
        }
        if (i >= s1.length()) {
            return false;
        }
        if (j >= s2.length()) {
            return false;
        }
        return s1.substring(i).compareTo(s2.substring(j)) == 0;
    }


    /**
     * 双指针解法，比较难想，还有一个问题是如何证明双指针是正确的？
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway2(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        int i = 0;
        while (i < first.length() && i < second.length() && first.charAt(i) == second.charAt(i)) {
            i++;
        }
        if (i == first.length() && i == second.length()) {
            return true;
        }
        int j = first.length() - 1, k = second.length() - 1;
        while (j >= 0 && k >= 0 && first.charAt(j) == second.charAt(k)) {
            j--;
            k--;
        }
        return j - i < 1 && k - i < 1;
    }

    /**
     * 分情况讨论
     *
     * 俗话说的，代码越长，性能越好
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway3(String first, String second) {
        int m = first.length();
        int n = second.length();
        if (Math.abs(m - n) > 1) {
            return false;
        }
        if (m == n) {
            //替换的场景
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    cnt++;
                    if (cnt > 1) {
                        return false;
                    }
                }
            }
            return true;
        } else if (m > n) {
            //删除的场景
            int i = 0, j = 0;
            int cnt = 0;
            while (i < m && j < n) {
                if (first.charAt(i) == second.charAt(j)) {
                    i++;
                    j++;
                } else {
                    cnt++;
                    if (cnt > 1) {
                        return false;
                    }
                    i++;
                }
            }
            if (i < m) {
                cnt += m - i;
            }
            if (j < n) {
                cnt += n - j;
            }
            return cnt <= 1;
        } else {
            //插入的场景
            int i = 0, j = 0;
            int cnt = 0;
            while (i < m && j < n) {
                if (first.charAt(i) == second.charAt(j)) {
                    i++;
                    j++;
                } else {
                    cnt++;
                    if (cnt > 1) {
                        return false;
                    }
                    j++;
                }
            }
            if (i < m) {
                cnt += m - i;
            }
            if (j < n) {
                cnt += n - j;
            }
            return cnt <= 1;
        }
    }
}
