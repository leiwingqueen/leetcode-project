package wc460

// 给你一个由大写英文字母组成的字符串 s。
//
// 你可以在字符串的 任意 位置（包括字符串的开头或结尾）最多插入一个 大写英文字母。
//
// 返回在 最多插入一个字母 后，字符串中可以形成的 "LCT" 子序列的 最大 数量。
//
// 子序列 是从另一个字符串中删除某些字符（可以不删除）且不改变剩余字符顺序后得到的一个 非空 字符串。
//
// 示例 1：
//
// 输入： s = "LMCT"
//
// 输出： 2
//
// 解释：
//
// 可以在字符串 s 的开头插入一个 "L"，变为 "LLMCT"，其中包含 2 个子序列，分别位于下标 [0, 3, 4] 和 [1, 3, 4]。
//
// 示例 2：
//
// 输入： s = "LCCT"
//
// 输出： 4
//
// 解释：
//
// 可以在字符串 s 的开头插入一个 "L"，变为 "LLCCT"，其中包含 4 个子序列，分别位于下标 [0, 2, 4]、[0, 3, 4]、[1, 2, 4] 和 [1, 3, 4]。
//
// 示例 3：
//
// 输入： s = "L"
//
// 输出： 0
//
// 解释：
//
// 插入一个字母无法获得子序列 "LCT"，结果为 0。
//
// 提示：
//
// 1 <= s.length <= 105
// s 仅由大写英文字母组成。

// 其实也就3个字符，分别尝试加L，加C，加T
// L一定是加在前面,T一定是加在后面
// C有点复杂，需要尝试每一个下标的情况
func numOfSubsequences(s string) int64 {
	// 先尝试插入L在头部
	n := len(s)
	suffixT, suffixCT := make([]int64, n+1), make([]int64, n+1)
	var total int64
	for i := n - 1; i >= 0; i-- {
		suffixT[i] = suffixT[i+1]
		suffixCT[i] = suffixCT[i+1]
		if s[i] == 'T' {
			suffixT[i]++
		} else if s[i] == 'C' {
			suffixCT[i] += suffixT[i+1]
		} else if s[i] == 'L' {
			total += suffixCT[i+1]
		}
	}
	// 尝试插入T在尾部
	prefixL, prefixLC := make([]int64, n+1), make([]int64, n+1)
	for i := 0; i < n; i++ {
		prefixL[i+1] = prefixL[i]
		prefixLC[i+1] = prefixLC[i]
		if s[i] == 'L' {
			prefixL[i+1]++
		} else if s[i] == 'C' {
			prefixLC[i+1] += prefixL[i]
		}
	}
	// 尝试在中间插入C
	var extra int64
	for i := 0; i < n; i++ {
		extra = max(extra, prefixL[i]*suffixT[i])
	}
	extra = max(extra, suffixCT[0], prefixLC[n])
	return total + extra
}
