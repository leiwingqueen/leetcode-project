package com.liyongquan.twopointer;

/**
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * <p>
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * 示例 2：
 * <p>
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 * 示例 3：
 * <p>
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *  
 * <p>
 * 提示：
 * <p>
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/long-pressed-name
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongPressedName {
    /**
     * 双指针解法
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        if (name.length() == 0 || typed.length() == 0) {
            return false;
        }
        if (name.charAt(0) != typed.charAt(0)) {
            return false;
        }
        int p1 = 1, p2 = 1;
        Character pre = typed.charAt(0);
        while (p1 < name.length() || p2 < typed.length()) {
            //name已经扫描完
            if (p1 >= name.length()) {
                while (p2 < typed.length() && typed.charAt(p2) == pre) {
                    p2++;
                }
                if (p2 < typed.length()) {
                    return false;
                } else {
                    return true;
                }
            }
            if (p2 >= typed.length()) {
                return false;
            }
            if (name.charAt(p1) == typed.charAt(p2)) {
                pre = typed.charAt(p2);
                p1++;
                p2++;
            } else if (typed.charAt(p2) == pre) {
                p2++;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据题意能够分析得到：字符串 \textit{typed}typed 的每个字符，有且只有两种「用途」：
     * <p>
     * 作为 \textit{name}name 的一部分。此时会「匹配」\textit{name}name 中的一个字符
     * <p>
     * 作为长按键入的一部分。此时它应当与前一个字符相同。
     * <p>
     * 如果 \textit{typed}typed 中存在一个字符，它两个条件均不满足，则应当直接返回 \textit{false}false；否则，当 \textit{typed}typed 扫描完毕后，我们再检查 \textit{name}name 的每个字符是否都被「匹配」了。
     * <p>
     * 实现上，我们使用两个下标 i,ji,j 追踪 \textit{name}name 和 \textit{typed}typed 的位置。
     * <p>
     * 当 \textit{name}[i]=\textit{typed}[j]name[i]=typed[j] 时，说明两个字符串存在一对匹配的字符，此时将 i,ji,j 都加 11。
     * <p>
     * 否则，如果 \textit{typed}[j]=\textit{typed}[j-1]typed[j]=typed[j−1]，说明存在一次长按键入，此时只将 jj 加 11。
     * <p>
     * 最后，如果 i=\textit{name}.\textit{length}i=name.length，说明 \textit{name}name 的每个字符都被「匹配」了。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/long-pressed-name/solution/chang-an-jian-ru-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isLongPressedName2(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
