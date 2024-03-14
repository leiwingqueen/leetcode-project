package greedy

// 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
//
//注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
//
//返回一个表示每个字符串片段的长度的列表。
//
//
//
//示例 1：
//输入：s = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
//示例 2：
//
//输入：s = "eccbbbbdec"
//输出：[10]
//
//
//提示：
//
//1 <= s.length <= 500
//s 仅由小写英文字母组成

func partitionLabels(s string) []int {
	n := len(s)
	first := make([]int, 26)
	last := make([]int, 26)
	for i := 0; i < 26; i++ {
		first[i] = -1
		last[i] = -1
	}
	for i := 0; i < n; i++ {
		idx := int(s[i] - 'a')
		if first[idx] < 0 {
			first[idx] = i
		}
		last[idx] = i
	}
	l, r := 0, 0
	var res []int
	for r < n {
		for i := 0; i <= r; i++ {
			idx := int(s[i] - 'a')
			if last[idx] > r {
				r = last[idx]
			}
		}
		res = append(res, r-l+1)
		l = r + 1
		r = l
	}
	return res
}

func partitionLabels2(s string) []int {
	n := len(s)
	last := make([]int, 26)
	for i := 0; i < n; i++ {
		idx := int(s[i] - 'a')
		last[idx] = i
	}
	l, r := 0, 0
	var res []int
	for r < n {
		for i := 0; i <= r; i++ {
			idx := int(s[i] - 'a')
			if last[idx] > r {
				r = last[idx]
			}
		}
		res = append(res, r-l+1)
		l = r + 1
		r = l
	}
	return res
}
