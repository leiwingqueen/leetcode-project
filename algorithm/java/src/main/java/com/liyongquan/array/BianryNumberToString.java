package com.liyongquan.array;

/**
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字不在0和1之间，或者无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * <p>
 * 示例1:
 * <p>
 * 输入：0.625
 * 输出："0.101"
 * 示例2:
 * <p>
 * 输入：0.1
 * 输出："ERROR"
 * 提示：0.1无法被二进制准确表示
 * 提示：
 * <p>
 * 32位包括输出中的"0."这两位。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bianry-number-to-string-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BianryNumberToString {
    /**
     * 朴素解法，乘2取1
     *
     * @param num
     * @return
     */
    public String printBin(double num) {
        StringBuilder builder = new StringBuilder(32);
        builder.append("0.");
        int count = 0;
        while (num != 0 && count < 30) {
            num *= 2;
            if (num >= 1) {
                builder.append('1');
                num--;
            } else {
                builder.append('0');
            }
            count++;
        }
        return num == 0 ? builder.toString() : "ERROR";
    }
}
