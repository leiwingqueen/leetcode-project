package twopointer

//1763. 最长的美好子字符串
//当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
//
//给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
//
// 
//
//示例 1：
//
//输入：s = "YazaAay"
//输出："aAa"
//解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
//"aAa" 是最长的美好子字符串。
//示例 2：
//
//输入：s = "Bb"
//输出："Bb"
//解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
//示例 3：
//
//输入：s = "c"
//输出：""
//解释：没有美好子字符串。
//示例 4：
//
//输入：s = "dDzeE"
//输出："dD"
//解释："dD" 和 "eE" 都是最长美好子字符串。
//由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
// 
//
//提示：
//
//1 <= s.length <= 100
//s 只包含大写和小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-nice-substring
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//解法1.暴力解法
func longestNiceSubstring(s string) string {
	max := 0
	res := ""
	for i := 0; i < len(s); i++ {
		for j := i + 1; j <= len(s); j++ {
			if j-i > max && match2(s, i, j) {
				max = j - i
				res = s[i:j]
			}
		}
	}
	return res
}

func match(s string, i int, j int) bool {
	mp1 := make([]int, 26)
	mp2 := make([]int, 26)
	for idx := i; idx < j; idx++ {
		if s[idx] >= 'a' && s[idx] <= 'z' {
			mp1[s[idx]-'a']++
		} else {
			mp2[s[idx]-'A']++
		}
	}
	for idx := 0; idx < 26; idx++ {
		if mp1[idx] > 0 && mp2[idx] == 0 || mp1[idx] == 0 && mp2[idx] > 0 {
			return false
		}
	}
	return true
}

//上面的基础上稍微优化一下
func match2(s string, i int, j int) bool {
	lower := 0
	upper := 0
	for idx := i; idx < j; idx++ {
		if s[idx] >= 'a' && s[idx] <= 'z' {
			lower |= 1 << (s[idx] - 'a')
		} else {
			upper |= 1 << (s[idx] - 'A')
		}
	}
	return lower^upper == 0
}

//分治解法
func longestNiceSubstring2(s string) string {
	return dfs([]byte(s), 0, len(s))
}

func dfs(arr []byte, start int, end int) string {
	//判断当前字符串是否合法
	lower := 0
	upper := 0
	for idx := start; idx < end; idx++ {
		if arr[idx] >= 'a' && arr[idx] <= 'z' {
			lower |= 1 << (arr[idx] - 'a')
		} else {
			upper |= 1 << (arr[idx] - 'A')
		}
	}
	xor := lower ^ upper
	if xor == 0 {
		return string(arr[start:end])
	}
	//根据单独出现的字符串进行分割
	l := start
	r := start
	res := ""
	for l < end {
		//找到第一个非单独出现的字符
		for l < end {
			idx := 0
			if arr[l] >= 'a' && arr[l] <= 'z' {
				idx = int(arr[l] - 'a')
			} else {
				idx = int(arr[l] - 'A')
			}
			if xor&(1<<idx) == 0 {
				break
			}
			l++
		}
		r = l
		for r < end {
			idx := 0
			if arr[r] >= 'a' && arr[r] <= 'z' {
				idx = int(arr[r] - 'a')
			} else {
				idx = int(arr[r] - 'A')
			}
			if xor&(1<<idx) != 0 {
				break
			}
			r++
		}
		if r-l > len(res) {
			sub := dfs(arr, l, r)
			if len(sub) > len(res) {
				res = sub
			}
		}
		l = r
	}
	return res
}

//TODO:是否还有其他更优的解法？
