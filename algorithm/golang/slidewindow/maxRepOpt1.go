package slidewindow

// 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
//
//给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
//
//
//
//示例 1：
//
//输入：text = "ababa"
//输出：3
//示例 2：
//
//输入：text = "aaabaaa"
//输出：6
//示例 3：
//
//输入：text = "aaabbaaa"
//输出：4
//示例 4：
//
//输入：text = "aaaaa"
//输出：5
//示例 5：
//
//输入：text = "abcdef"
//输出：1
//
//
//提示：
//
//1 <= text.length <= 20000
//text 仅由小写英文字母组成。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/swap-for-longest-repeated-character-substring
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxRepOpt1(text string) int {
	n := len(text)
	// 预处理每个字符的出现次数
	cnt := make([]int, 26)
	for i := 0; i < n; i++ {
		cnt[text[i]-'a']++
	}
	i, j := 0, 0
	res := 0
	for i < n {
		for j < n && text[j] == text[i] {
			j++
		}
		if j-i > res {
			res = j - i
		}
		// 替换一个字符
		if cnt[text[i]-'a'] > j-i {
			k := j + 1
			for k < n && text[k] == text[i] {
				k++
			}
			l := k - i
			if cnt[text[i]-'a'] < k-i {
				l = cnt[text[i]-'a']
			}
			if l > res {
				res = l
			}
		}
		i = j
	}
	return res
}
