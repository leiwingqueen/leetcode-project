package math

//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
//
//
//
//示例 1:
//
//输入: num = 100
//输出: "202"
//示例 2:
//
//输入: num = -7
//输出: "-10"
//
//
//提示：
//
//-107 <= num <= 107
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/base-7
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func convertToBase7(num int) string {
	res := make([]byte, 0)
	minus := false
	if num < 0 {
		num = -num
		minus = true
	}
	for num > 0 {
		mod := num % 7
		num /= 7
		//头部插入
		res = append([]byte{byte('0' + mod)}, res...)
	}
	if minus {
		res = append([]byte{'-'}, res...)
	}
	if len(res) == 0 {
		res = append(res, '0')
	}
	return string(res)
}
