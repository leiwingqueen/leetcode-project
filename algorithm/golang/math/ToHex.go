package math

import "strings"

//给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
//
//注意:
//
//十六进制中所有字母(a-f)都必须是小写。
//十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
//给定的数确保在32位有符号整数范围内。
//不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
//示例 1：
//
//输入:
//26
//
//输出:
//"1a"
//示例 2：
//
//输入:
//-1
//
//输出:
//"ffffffff"
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func toHex(num int) string {
	sb := strings.Builder{}
	//要转成unit来处理无符号右移
	n := uint(num)
	for n != 0 {
		mod := n & 0xf
		ch := '0' + mod
		if mod >= 10 {
			ch = 'a' + mod - 10
		}
		sb.WriteByte(byte(ch))
		n >>= 4
	}
	if sb.Len() == 0 {
		return "0"
	}
	res := make([]byte, sb.Len())
	for i := 0; i < sb.Len(); i++ {
		res[i] = sb.String()[sb.Len()-i-1]
	}
	return string(res)
}
