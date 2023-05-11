package string

import (
	"strconv"
	"strings"
)

//给定一个二进制字符串 s 和一个正整数 n，如果对于 [1, n] 范围内的每个整数，其二进制表示都是 s 的 子字符串 ，就返回 true，否则返回
//false 。
//
// 子字符串 是字符串中连续的字符序列。
//
//
//
// 示例 1：
//
//
//输入：s = "0110", n = 3
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "0110", n = 4
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s[i] 不是 '0' 就是 '1'
// 1 <= n <= 10⁹
//
//
// Related Topics 字符串 👍 90 👎 0

// 暴力解法
func queryString(s string, n int) bool {
	for i := 1; i <= n; i++ {
		f := strconv.FormatUint(uint64(i), 2)
		if !strings.Contains(s, f) {
			return false
		}
	}
	return true
}

// 逆向思维，然而这写法比上面的写法还慢
func queryString2(s string, n int) bool {
	convert := func(str string) int {
		num := 0
		for i := 0; i < len(str); i++ {
			num = num<<1 + int(str[i]-'0')
		}
		return num
	}
	mp := make(map[int]struct{})
	k := len(s)
	for i := 0; i < k; i++ {
		for j := i; j < k && j-i+1 <= 30; j++ {
			sub := s[i : j+1]
			num := convert(sub)
			if num >= 1 && num <= n {
				mp[num] = struct{}{}
			}
		}
	}
	return len(mp) == n
}
