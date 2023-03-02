package math

import "strings"

// 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
//
//示例1:
//
// 输入：0.625
// 输出："0.101"
//示例2:
//
// 输入：0.1
// 输出："ERROR"
// 提示：0.1无法被二进制准确表示
//
//
//提示：
//
//32位包括输出中的 "0." 这两位。
//题目保证输入用例的小数位数最多只有 6 位
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/bianry-number-to-string-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func printBin(num float64) string {
	builder := strings.Builder{}
	builder.WriteString("0.")
	cnt := 0
	for num > 0 && cnt < 30 {
		num *= 2
		if num >= 1 {
			builder.WriteByte('1')
			num -= 1
		} else {
			builder.WriteByte('0')
		}
		cnt++
	}
	if num > 0 {
		return "ERROR"
	} else {
		return builder.String()
	}
}
