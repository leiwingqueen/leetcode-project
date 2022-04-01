package com.liyongquan.hash;

//给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
//
// 
//
//示例 1：
//
//输入：arr = [3,1,3,6]
//输出：false
//示例 2：
//
//输入：arr = [2,1,2,6]
//输出：false
//示例 3：
//
//输入：arr = [4,-2,2,-4]
//输出：true
//解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
// 
//
//提示：
//
//0 <= arr.length <= 3 * 104
//arr.length 是偶数
//-105 <= arr[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Map;
import java.util.TreeMap;

public class CanReorderDoubled {
    public boolean canReorderDoubled(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        while (map.size() > 0) {
            Map.Entry<Integer, Integer> first = map.pollFirstEntry();
            Integer num = first.getKey();
            Integer cnt = first.getValue();
            if (num == 0) {
                if (cnt % 2 != 0) {
                    return false;
                }
            } else if (num > 0) {
                int next = num * 2;
                if (map.getOrDefault(next, 0) < cnt) {
                    return false;
                }
                map.put(next, map.get(next) - cnt);
                if (map.get(next) == 0) {
                    map.remove(next);
                }
            } else {
                if (Math.abs(num) % 2 != 0) {
                    return false;
                }
                int next = num / 2;
                if (map.getOrDefault(next, 0) < cnt) {
                    return false;
                }
                map.put(next, map.get(next) - cnt);
                if (map.get(next) == 0) {
                    map.remove(next);
                }
            }
        }
        return true;
    }
}
