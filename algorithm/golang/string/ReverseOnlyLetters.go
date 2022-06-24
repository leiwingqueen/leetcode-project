package string

//给你一个字符串 s ，根据下述规则反转字符串：
//
//所有非英文字母保留在原有位置。
//所有英文字母（小写或大写）位置反转。
//返回反转后的 s 。
//
//
//
//示例 1：
//
//输入：s = "ab-cd"
//输出："dc-ba"
//示例 2：
//
//输入：s = "a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
//示例 3：
//
//输入：s = "Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
//
//
//提示
//
//1 <= s.length <= 100
//s 仅由 ASCII 值在范围 [33, 122] 的字符组成
//s 不含 '\"' 或 '\\'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-only-letters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func reverseOnlyLetters(s string) string {
	arr := []byte(s)
	l := 0
	r := len(arr) - 1
	for l < r {
		for l < r && !isLetter(arr[l]) {
			l++
		}
		for l < r && !isLetter(arr[r]) {
			r--
		}
		b := arr[l]
		arr[l] = arr[r]
		arr[r] = b
		l++
		r--
	}
	return string(arr)
}

func isLetter(b byte) bool {
	return b >= 'a' && b <= 'z' || b >= 'A' && b <= 'Z'
}
