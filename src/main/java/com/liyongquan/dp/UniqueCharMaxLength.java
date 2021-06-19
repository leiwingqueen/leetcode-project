package com.liyongquan.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. 串联字符串的最大长度
 * <p>
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 * <p>
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 * <p>
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniqueCharMaxLength {
    /**
     * 回溯解法
     * <p>
     * 还有优化空间，对于一个字符看有没有重复出现我们可以通过位运算进行优化
     *
     * @param arr
     * @return
     */
    public int maxLength(List<String> arr) {
        return backtrace(arr, 0, new int[26]);
    }

    private int backtrace(List<String> arr, int idx, int[] bitmap) {
        if (idx == arr.size()) {
            return 0;
        }
        String s = arr.get(idx);
        //不选
        int res = backtrace(arr, idx + 1, bitmap);
        //选
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            bitmap[ch - 'a']++;
            if (bitmap[ch - 'a'] > 1) {
                break;
            }
            i++;
        }
        if (i == s.length()) {
            int r = backtrace(arr, idx + 1, bitmap) + s.length();
            res = Math.max(res, r);
            i--;
        }
        //状态回滚
        while (i >= 0) {
            char ch = s.charAt(i);
            bitmap[ch - 'a']--;
            i--;
        }
        return res;
    }

    /**
     * 位运算，优化解法
     *
     * @param arr
     * @return
     */
    public int maxLength2(List<String> arr) {
        List<Integer> list = new ArrayList<>(arr.size());
        //字符串转成int(26个字母，每个字母使用一个bit进行存储就足够了)
        for (String s : arr) {
            int num = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                int bit = 1 << ch - 'a';
                //重复出现
                if ((num & bit) != 0) {
                    num = 0;
                    break;
                }
                num |= bit;
            }
            if (num > 0) {
                list.add(num);
            }
        }
        return backtrace(list, 0, 0);
    }

    private int backtrace(List<Integer> arr, int idx, int bitmap) {
        if (idx == arr.size()) {
            return 0;
        }
        int num = arr.get(idx);
        //不选
        int res = backtrace(arr, idx + 1, bitmap);
        //选
        if ((bitmap & num) == 0) {
            int r = backtrace(arr, idx + 1, bitmap | num) + oneCount(num);
            res = Math.max(res, r);
        }
        return res;
    }

    private int oneCount(int num) {
        int cnt = 0;
        while (num > 0) {
            if ((num & 0b1) == 1) {
                cnt++;
            }
            num >>= 1;
        }
        return cnt;
    }
}
