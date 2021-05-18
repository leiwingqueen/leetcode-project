package com.liyongquan.bit;

import java.util.*;

/**
 * 1442. 形成两个异或相等数组的三元组数目
 * <p>
 * 给你一个整数数组 arr 。
 * <p>
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * <p>
 * a 和 b 定义如下：
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * <p>
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * <p>
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * <p>
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * <p>
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountTriplets {
    /**
     * 暴力
     * <p>
     * 时间复杂度O(n^3)
     * 空间复杂度O(n^3)
     *
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        int len = arr.length;
        //前缀和
        int[] prefix = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefix[i] = prefix[i - 1] ^ arr[i - 1];
        }
        int cnt = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j; k < len; k++) {
                    int left = prefix[j] ^ prefix[i];
                    int right = prefix[k + 1] ^ prefix[j];
                    if (left == right) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 异或满足交换律，可以做下优化
     *
     * @param arr
     * @return
     */
    public int countTriplets2(int[] arr) {
        int len = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        map.put(0, list);
        //前缀和
        int pre = 0;
        for (int i = 0; i < len; i++) {
            pre ^= arr[i];
            if (map.containsKey(pre)) {
                List<Integer> l = map.get(pre);
                l.add(i + 1);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i + 1);
                map.put(pre, l);
            }
        }
        int cnt = 0;
        for (List<Integer> l : map.values()) {
            for (int i = 0; i < l.size(); i++) {
                for (int j = i + 1; j < l.size(); j++) {
                    cnt += l.get(j) - l.get(i) - 1;
                }
            }
        }
        return cnt;
    }
}
