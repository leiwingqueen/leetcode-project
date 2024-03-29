package com.liyongquan.array;

//在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
//
//如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
//
//age[y] <= 0.5 * age[x] + 7
//age[y] > age[x]
//age[y] > 100 && age[x] < 100
//否则，x 将会向 y 发送一条好友请求。
//
//注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
//
//返回在该社交媒体网站上产生的好友请求总数。
//
// 
//
//示例 1：
//
//输入：ages = [16,16]
//输出：2
//解释：2 人互发好友请求。
//示例 2：
//
//输入：ages = [16,17,18]
//输出：2
//解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
//示例 3：
//
//输入：ages = [20,30,100,110,120]
//输出：3
//解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
// 
//
//提示：
//
//n == ages.length
//1 <= n <= 2 * 104
//1 <= ages[i] <= 120
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;

public class NumFriendRequests {
    /**
     * 图解法，满足条件连一条线
     * <p>
     * 时间复杂度O(n^2)
     *
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        int len = ages.length;
        int cnt = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (match(ages, i, j)) {
                    cnt++;
                }
                if (match(ages, j, i)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    //age[y] <= 0.5 * age[x] + 7
    // age[y] > age[x]
    // age[y] > 100 && age[x] < 100
    private boolean match(int[] ages, int x, int y) {
        if (ages[y] <= 0.5 * ages[x] + 7 || ages[y] > ages[x] || (ages[y] > 100 && ages[x] < 100)) {
            return false;
        }
        return true;
    }

    /**
     * 解法2，经过梳理后的数学公式可以简化成 x>=y>0.5*x+7，
     * <p>
     * y<=x
     * y>0.5x+7
     *
     * 时间复杂度O(nlogn)
     */
    public int numFriendRequests2(int[] ages) {
        //排序
        Arrays.sort(ages);
        int cnt = 0;
        for (int i = 0; i < ages.length; i++) {
            //二分查找找到满足条件的y值，需要左右两边同时查找
            int left = searchLeft(ages, i);
            if (left >= 0) {
                //[idx,i)
                cnt += i - left;
            }
            int right = searchRight(ages, i);
            if (right > i) {
                cnt += right - i;
            }
        }
        return cnt;
    }

    //二分查找左边界
    private int searchLeft(int[] ages, int idx) {
        if (idx == 0) {
            return -1;
        }
        double value = 0.5 * ages[idx] + 7;
        if (ages[idx - 1] <= value) {
            return -1;
        }
        int l = 0, r = idx - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (ages[mid] > value) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //右边界其实就是找相等的数字的右边界，y<=x,y>0.5x+7，右边也有可能满足这个条件。y==x，且y>0.5x+7，最终换算得到y>14
    private int searchRight(int[] ages, int idx) {
        //上面条件y>14
        if (ages[idx] <= 14) {
            return -1;
        }
        int l = idx, r = ages.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (ages[mid] == ages[idx]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
