package dp

import "leetcode-go/util"

// 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
//
//你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
//
//请你返回使 s 平衡 的 最少 删除次数。
//
//
//
//示例 1：
//
//输入：s = "aababbab"
//输出：2
//解释：你可以选择以下任意一种方案：
//下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
//下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
//示例 2：
//
//输入：s = "bbaaaaabb"
//输出：2
//解释：唯一的最优解是删除最前面两个字符。
//
//
//提示：
//
//1 <= s.length <= 105
//s[i] 要么是 'a' 要么是 'b'​ 。​
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// dp解法，才击败了8%?
func minimumDeletions(s string) int {
	n := len(s)
	// 这一步计数的可以省略
	cnt := make([]int, n+1)
	for i := 0; i < n; i++ {
		cnt[i+1] = cnt[i]
		if s[i] == 'b' {
			cnt[i+1]++
		}
	}
	f0 := 0
	f1 := 0
	for i := 1; i < n; i++ {
		if s[i] == 'b' {
			f1 = f0
		} else {
			f1 = util.Min(f0+1, cnt[i])
		}
		f0 = f1
	}
	return f0
}

// 优化上面的空间存储
func minimumDeletions2(s string) int {
	n := len(s)
	cnt := 0
	if s[0] == 'b' {
		cnt++
	}
	f0 := 0
	f1 := 0
	for i := 1; i < n; i++ {
		if s[i] == 'b' {
			f1 = f0
			cnt++
		} else {
			f1 = util.Min(f0+1, cnt)
		}
		f0 = f1
	}
	return f0
}
