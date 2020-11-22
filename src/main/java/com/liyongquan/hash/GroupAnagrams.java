package com.liyongquan.hash;

import java.util.*;

/**
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * <p>
 * 注意：本题相对原题稍作修改
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] bitmap = new int[26];
            for (int i = 0; i < str.length(); i++) {
                bitmap[str.charAt(i) - 'a']++;
            }
            //不能直接使用int[]作为key，因为hashcode不一样。这里转成eat转成a1e1t1的格式
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (bitmap[i] > 0) {
                    sb.append((char) (i + 'a') + String.valueOf(bitmap[i]));
                }
            }
            //System.out.println(bitmap.hashCode());
            List<String> list = map.getOrDefault(sb.toString(), new LinkedList<>());
            list.add(str);
            map.put(sb.toString(), list);
        }
        List<List<String>> result = new LinkedList<>();
        result.addAll(map.values());
        return result;
    }

    /**
     * 使用排序
     * <p>
     * 时间复杂度O(k*nlogn)，k个字符串，每个字符串的字符长度为n
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            List<String> list = map.getOrDefault(new String(s), new LinkedList<>());
            list.add(str);
            map.put(new String(s), list);
        }
        List<List<String>> result = new LinkedList<>();
        result.addAll(map.values());
        return result;
    }

}
