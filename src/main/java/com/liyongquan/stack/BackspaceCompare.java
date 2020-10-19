package com.liyongquan.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BackspaceCompare {
    /**
     * 典型的栈数据结构
     * <p>
     * 时间复杂度O(S+T)，空间复杂度O(S+T)
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        Deque<Character> s1 = new LinkedList<>(), s2 = new LinkedList<>();
        //使用栈存储最终的输出结果
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#') {
                if (s1.size() > 0) {
                    s1.pollFirst();
                }
            } else {
                s1.offerFirst(S.charAt(i));
            }
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) == '#') {
                if (s2.size() > 0) {
                    s2.pollFirst();
                }
            } else {
                s2.offerFirst(T.charAt(i));
            }
        }
        if (s1.size() != s2.size()) {
            return false;
        }
        //一个个字符进行对比
        while (s1.size() > 0) {
            if (s1.pollFirst() != s2.pollFirst()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 双指针，解法还是很妙的
     * <p>
     * 一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
     * <p>
     * 具体地，我们定义 \textit{skip}skip 表示当前待删除的字符的数量。每次我们遍历到一个字符：
     * <p>
     * 若该字符为退格符，则我们需要多删除一个普通字符，我们让 \textit{skip}skip 加 11；
     * <p>
     * 若该字符为普通字符：
     * <p>
     * 若 \textit{skip}skip 为 00，则说明当前字符不需要删去；
     * <p>
     * 若 \textit{skip}skip 不为 00，则说明当前字符需要删去，我们让 \textit{skip}skip 减 11。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/backspace-string-compare/solution/bi-jiao-han-tui-ge-de-zi-fu-chuan-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 时间复杂度O(max{S,T}),空间复杂度O(1)
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare2(String S, String T) {
        int p1 = S.length() - 1, p2 = T.length() - 1;
        int skip1 = 0, skip2 = 0;
        while (p1 >= 0 || p2 >= 0) {
            //找到第一个有效的字符
            while (p1 >= 0) {
                if (S.charAt(p1) == '#') {
                    skip1++;
                    p1--;
                } else if (skip1 > 0) {
                    skip1--;
                    p1--;
                } else {
                    break;
                }
            }
            while (p2 >= 0) {
                if (T.charAt(p2) == '#') {
                    skip2++;
                    p2--;
                } else if (skip2 > 0) {
                    skip2--;
                    p2--;
                } else {
                    break;
                }
            }
            //对比
            if (p1 >= 0 && p2 >= 0) {
                if (S.charAt(p1) != T.charAt(p2)) {
                    return false;
                }
            } else if (p1 < 0 && p2 < 0) {
                return true;
            } else {
                return false;
            }
            p1--;
            p2--;
        }
        return true;
    }
}
