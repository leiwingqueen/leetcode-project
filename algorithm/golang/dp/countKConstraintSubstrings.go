package dp

import "sort"

// 给你一个 二进制 字符串 s 和一个整数 k。
//
//另给你一个二维整数数组 queries ，其中 queries[i] = [li, ri] 。
//
//如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：
//
//字符串中 0 的数量最多为 k。
//字符串中 1 的数量最多为 k。
//返回一个整数数组 answer ，其中 answer[i] 表示 s[li..ri] 中满足 k 约束 的
//子字符串
// 的数量。
//
//
//
//示例 1：
//
//输入：s = "0001111", k = 2, queries = [[0,6]]
//
//输出：[26]
//
//解释：
//
//对于查询 [0, 6]， s[0..6] = "0001111" 的所有子字符串中，除 s[0..5] = "000111" 和 s[0..6] = "0001111" 外，其余子字符串都满足 k 约束。
//
//示例 2：
//
//输入：s = "010101", k = 1, queries = [[0,5],[1,4],[2,3]]
//
//输出：[15,9,3]
//
//解释：
//
//s 的所有子字符串中，长度大于 3 的子字符串都不满足 k 约束。
//
//
//
//提示：
//
//1 <= s.length <= 105
//s[i] 是 '0' 或 '1'
//1 <= k <= s.length
//1 <= queries.length <= 105
//queries[i] == [li, ri]
//0 <= li <= ri < s.length
//所有查询互不相同

// 超时
func countKConstraintSubstrings(s string, k int, queries [][]int) []int64 {
	cal := func(query []int) int64 {
		var res int64
		l, r := query[0], query[0]
		cnt0, cnt1 := 0, 0
		for r <= query[1]+1 {
			if l == r || cnt0 <= k || cnt1 <= k {
				res += int64(r - l)
				if r == query[1]+1 {
					return res
				}
				if s[r] == '0' {
					cnt0++
				} else {
					cnt1++
				}
				r++
			} else {
				if s[l] == '0' {
					cnt0--
				} else {
					cnt1--
				}
				l++
			}
		}
		return res
	}
	res := make([]int64, len(queries))
	for i, query := range queries {
		res[i] = cal(query)
	}
	return res
}

func countKConstraintSubstrings2(s string, k int, queries [][]int) []int64 {
	// 计算每个下标的合法的左边界
	n := len(s)
	left := make([]int, n)
	l, r := 0, 0
	cnt0, cnt1 := 0, 0
	for r < n {
		if s[r] == '0' {
			cnt0++
		} else {
			cnt1++
		}
		r++
		if cnt0 <= k || cnt1 <= k {
			left[r-1] = l
		} else {
			// 左边窗口移动
			for cnt0 > k && cnt1 > k {
				if s[l] == '0' {
					cnt0--
				} else {
					cnt1--
				}
				l++
			}
			left[r-1] = l
		}
	}
	prefix := make([]int64, n+1)
	for i := 0; i < n; i++ {
		prefix[i+1] = prefix[i] + int64(i-left[i]+1)
	}
	cal := func(query []int) int64 {
		p1, p2 := query[0], query[1]
		// 假设p2的左边界<=p1，那么所有的子数组均满足条件
		if left[p2] <= p1 {
			return int64(p2-p1+2) * int64(p2-p1+1) / 2
		}
		// 否则就得分两种情况考虑，先找到最后一个的下标的左边界<=p1(等价于找到第一个左边界>p1)
		idx := sort.Search(n, func(i int) bool {
			return left[i] > p1
		})
		idx--
		// [p1,idx]的所有子串都能满足条件
		res := int64(idx-p1+2) * int64(idx-p1+1) / 2
		// 另外需要计算所有下标为[idx+1,p2]的前缀和
		res += prefix[p2+1] - prefix[idx+1]
		return res
	}
	res := make([]int64, len(queries))
	for i, query := range queries {
		res[i] = cal(query)
	}
	return res
}
