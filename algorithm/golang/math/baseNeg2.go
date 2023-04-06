package math

// 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
//
//注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
//
//
//
//示例 1：
//
//输入：n = 2
//输出："110"
//解释：(-2)2 + (-2)1 = 2
//示例 2：
//
//输入：n = 3
//输出："111"
//解释：(-2)2 + (-2)1 + (-2)0 = 3
//示例 3：
//
//输入：n = 4
//输出："100"
//解释：(-2)2 = 4
//
//
//提示：
//
//0 <= n <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/convert-to-base-2
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 错误
func baseNeg2(n int) string {
	var res []byte
	k := 1
	for n != 0 {
		sub := n - k
		k2 := k * -2
		choose := false
		if k > 0 {
			if sub <= k2 || sub > k {
				choose = true
			}
		} else {
			if sub >= k2 || sub < k {
				choose = true
			}
		}
		if choose {
			res = append([]byte{'1'}, res...)
			n -= k
		} else {
			res = append([]byte{'0'}, res...)
		}
		k *= -2
	}
	return string(res)
}

// 通过，但是发这个太难想了
func baseNeg3(n int) string {
	if n == 0 {
		return "0"
	}
	var res []byte
	idx := 0
	for n != 0 {
		if n&1 == 1 {
			res = append([]byte{'1'}, res...)
			if idx&1 != 0 {
				n += 2
			}
		} else {
			res = append([]byte{'0'}, res...)
		}
		n >>= 1
		idx++
	}
	return string(res)
}
