package array

// 一个字符串如果没有 三个连续 相同字符，那么它就是一个 好字符串 。
//
//给你一个字符串 s ，请你从 s 删除 最少 的字符，使它变成一个 好字符串 。
//
//请你返回删除后的字符串。题目数据保证答案总是 唯一的 。
//
//
//
//示例 1：
//
//输入：s = "leeetcode"
//输出："leetcode"
//解释：
//从第一组 'e' 里面删除一个 'e' ，得到 "leetcode" 。
//没有连续三个相同字符，所以返回 "leetcode" 。
//示例 2：
//
//输入：s = "aaabaaaa"
//输出："aabaa"
//解释：
//从第一组 'a' 里面删除一个 'a' ，得到 "aabaaaa" 。
//从第二组 'a' 里面删除两个 'a' ，得到 "aabaa" 。
//没有连续三个相同字符，所以返回 "aabaa" 。
//示例 3：
//
//输入：s = "aab"
//输出："aab"
//解释：没有连续三个相同字符，所以返回 "aab" 。
//
//
//提示：
//
//1 <= s.length <= 105
//s 只包含小写英文字母。

func makeFancyString(s string) string {
	n := len(s)
	var arr []byte
	for i := 0; i < n; i++ {
		if len(arr) < 2 || s[i] != arr[len(arr)-1] || s[i] != arr[len(arr)-2] {
			arr = append(arr, s[i])
		}
	}
	return string(arr)
}
