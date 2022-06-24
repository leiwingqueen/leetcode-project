package com.liyongquan.string;

/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * <p>
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * <p>
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "USA"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "FlaG"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/detect-capital
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DetectCapital {
    /**
     * 第一维数组表示状态，第二维数组表示大小写转换
     */
    int[][] state = new int[][]{
            {1, 2},
            {3, 2},
            {4, 2},
            {3, 4}
    };

    /**
     * 用状态机解决，时间复杂度O(n)
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        int s = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int lowercase = Character.isLowerCase(c) ? 1 : 0;
            s = state[s][lowercase];
            if (s >= 4) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        DetectCapital capital = new DetectCapital();
        boolean flaG = capital.detectCapitalUse("FlaG");
        System.out.println(flaG);
    }
}
