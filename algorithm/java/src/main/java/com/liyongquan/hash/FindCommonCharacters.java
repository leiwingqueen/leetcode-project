package com.liyongquan.hash;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindCommonCharacters {
    /**
     * 时间复杂度O(n)
     *
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        if (A.length == 0) {
            return Collections.EMPTY_LIST;
        }
        int[] result = new int[26];
        int[] cur = new int[26];
        for (int i = 0; i < A[0].length(); i++) {
            result[A[0].charAt(i) - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < A[i].length(); j++) {
                cur[A[i].charAt(j) - 'a']++;
            }
            //合并到result
            for (int j = 0; j < 26; j++) {
                result[j] = Math.min(result[j], cur[j]);
                cur[j] = 0;
            }
        }
        List<String> list = new LinkedList<>();
        for (int i = 0; i < result.length; i++) {
            if (result[i] > 0) {
                for (int j = 0; j < result[i]; j++) {
                    list.add(new String(new char[]{(char) (i + 'a')}));
                }
            }
        }
        return list;
    }
}
