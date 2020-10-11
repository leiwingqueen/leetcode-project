package com.liyongquan.binarySort;

/**
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 * <p>
 * 示例1:
 * <p>
 * 输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 * 输出：-1
 * 说明: 不存在返回-1。
 * 示例2:
 * <p>
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 * 输出：4
 * 提示:
 * <p>
 * words的长度在[1, 1000000]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sparse-array-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SparseArraySearch {
    /**
     * 傻瓜查找
     *
     * @param words
     * @param s
     * @return
     */
    public int findString2(String[] words, String s) {
        if (words.length == 0 || s.length() == 0) {
            return -1;
        }
        for (int i = 0; i < words.length; i++) {
            if (words[i].compareTo(s) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 简单二分查找，这种方式不行，可能导致死循环
     *
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        if (words.length == 0 || s.length() == 0) {
            return -1;
        }
        int left = 0, right = words.length - 1;
        while (left <= right) {
            //去掉左边空格(这个一定要加，不然会导致死循环)
            while (left <= right && words[left].length() == 0) {
                left++;
            }
            //去掉右边空格(这个一定要加，不然会导致死循环)
            while (left <= right && words[right].length() == 0) {
                right--;
            }
            int middle = (left + right) >> 1;
            //尝试一步一步往左走，但是如果空串很多，这种方式查找会比较慢
            while (middle >= 0 && words[middle].length() == 0) {
                middle--;
            }
            int c = words[middle].compareTo(s);
            if (c == 0) {
                return middle;
            } else if (c > 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
