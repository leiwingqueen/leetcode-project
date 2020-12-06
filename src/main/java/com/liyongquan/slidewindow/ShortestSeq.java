package com.liyongquan.slidewindow;

import javax.naming.BinaryRefAddr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 * <p>
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 * 示例 2:
 * <p>
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 * 提示：
 * <p>
 * big.length <= 100000
 * 1 <= small.length <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-supersequence-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ShortestSeq {
    /**
     * 这写法有点绕，还有一个问题是如何证明这样移动是正确的？
     *
     * @param big
     * @param small
     * @return
     */
    public int[] shortestSeq(int[] big, int[] small) {
        //初始化small的set
        Set<Integer> set = new HashSet<>(small.length);
        for (int s : small) {
            set.add(s);
        }
        int l = 0, r = 0;
        //满足条件的数量
        int count = 0;
        int res = big.length + 1;
        int[] idx = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        while (r < big.length) {
            //右边界移动
            while (r < big.length && count < set.size()) {
                int num = big[r];
                if (set.contains(num)) {
                    Integer c = map.getOrDefault(num, 0);
                    map.put(num, c + 1);
                    if (c == 0) {
                        count++;
                    }
                }
                r++;
            }
            //找不到结果，直接退出
            if (count < set.size()) {
                return res > big.length ? new int[]{} : idx;
            }
            //左边界移动
            while (l < r && count >= set.size()) {
                int num = big[l];
                if (set.contains(num)) {
                    Integer c = map.get(num);
                    map.put(num, c - 1);
                    if (c == 1) {
                        count--;
                    }
                }
                l++;
            }
            //更新结果
            int len = r - l + 1;
            if (len < res) {
                res = len;
                idx[0] = l - 1;
                idx[1] = r - 1;
            }
        }
        return res > big.length ? new int[]{} : idx;
    }
}
