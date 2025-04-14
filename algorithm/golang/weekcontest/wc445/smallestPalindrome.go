package wc445

// 给你一个 回文 字符串 s。
//
//返回 s 的按字典序排列的 最小 回文排列。
//
//如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个 回文 字符串。
//
//排列 是字符串中所有字符的重排。
//
//如果字符串 a 按字典序小于字符串 b，则表示在第一个不同的位置，a 中的字符比 b 中的对应字符在字母表中更靠前。
//如果在前 min(a.length, b.length) 个字符中没有区别，则较短的字符串按字典序更小。
//
//
//
//
//示例 1：
//
//输入： s = "z"
//
//输出： "z"
//
//解释：
//
//仅由一个字符组成的字符串已经是按字典序最小的回文。
//
//示例 2：
//
//输入： s = "babab"
//
//输出： "abbba"
//
//解释：
//
//通过重排 "babab" → "abbba"，可以得到按字典序最小的回文。
//
//示例 3：
//
//输入： s = "daccad"
//
//输出： "acddca"
//
//解释：
//
//通过重排 "daccad" → "acddca"，可以得到按字典序最小的回文。
//
//
//
//提示：
//
//1 <= s.length <= 105
//s 由小写英文字母组成。
//保证 s 是回文字符串。

func smallestPalindrome(s string) string {
	n := len(s)
	cnt := make([]int, 26)
	for i := 0; i < n; i++ {
		cnt[int(s[i]-'a')]++
	}
	arr := make([]byte, n)
	l, r := 0, n-1
	for l < r {
		for i := 0; i < 26; i++ {
			if cnt[i] > 1 {
				arr[l] = byte('a' + i)
				arr[r] = byte('a' + i)
				cnt[i] -= 2
				l++
				r--
				break
			}
		}
	}
	// 如果是奇数，最后一定剩下一个数字
	if n%2 == 1 {
		for i := 0; i < 26; i++ {
			if cnt[i] > 0 {
				arr[n/2] = byte('a' + i)
				break
			}
		}
	}
	return string(arr)
}
