package greedy

// 给你一个字符串 s ，请你判断它是否 有效 。
//字符串 s 有效 需要满足：假设开始有一个空字符串 t = "" ，你可以执行 任意次 下述操作将 t 转换为 s ：
//
//将字符串 "abc" 插入到 t 中的任意位置。形式上，t 变为 tleft + "abc" + tright，其中 t == tleft + tright 。注意，tleft 和 tright 可能为 空 。
//如果字符串 s 有效，则返回 true；否则，返回 false。
//
//
//
//示例 1：
//
//输入：s = "aabcbc"
//输出：true
//解释：
//"" -> "abc" -> "aabcbc"
//因此，"aabcbc" 有效。
//示例 2：
//
//输入：s = "abcabcababcc"
//输出：true
//解释：
//"" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
//因此，"abcabcababcc" 有效。
//示例 3：
//
//输入：s = "abccba"
//输出：false
//解释：执行操作无法得到 "abccba" 。
//
//
//提示：
//
//1 <= s.length <= 2 * 104
//s 由字母 'a'、'b' 和 'c' 组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func isValid(s string) bool {
	if s[0] != 'a' {
		return false
	}
	n := len(s)
	aCnt, bCnt, cCnt := 0, 0, 0
	arr := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		if s[i] == 'a' {
			arr[i] = bCnt
			aCnt++
		} else if s[i] == 'b' {
			arr[i] = cCnt
			bCnt++
		} else {
			arr[i] = 1
			cCnt++
		}
	}
	for i := 0; i < n; i++ {
		if arr[i] <= 0 {
			return false
		}
	}
	return true
}

func isValid2(s string) bool {
	var stack []byte
	for i := 0; i < len(s); i++ {
		if s[i] != 'c' {
			stack = append(stack, s[i])
		} else {
			if len(stack) < 2 {
				return false
			}
			if stack[len(stack)-1] != 'b' || stack[len(stack)-2] != 'a' {
				return false
			}
			stack = stack[:len(stack)-2]
		}
	}
	return len(stack) == 0
}
