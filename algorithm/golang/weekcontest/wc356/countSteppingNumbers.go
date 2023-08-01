package wc356

//给你两个正整数 low 和 high ，都用字符串表示，请你统计闭区间 [low, high] 内的 步进数字 数目。
//
// 如果一个整数相邻数位之间差的绝对值都 恰好 是 1 ，那么这个数字被称为 步进数字 。
//
// 请你返回一个整数，表示闭区间 [low, high] 之间步进数字的数目。
//
// 由于答案可能很大，请你将它对 10⁹ + 7 取余 后返回。
//
// 注意：步进数字不能有前导 0 。
//
//
//
// 示例 1：
//
// 输入：low = "1", high = "11"
//输出：10
//解释：区间 [1,11] 内的步进数字为 1 ，2 ，3 ，4 ，5 ，6 ，7 ，8 ，9 和 10 。总共有 10 个步进数字。所以输出为 10 。
//
// 示例 2：
//
// 输入：low = "90", high = "101"
//输出：2
//解释：区间 [90,101] 内的步进数字为 98 和 101 。总共有 2 个步进数字。所以输出为 2 。
//
//
//
// 提示：
//
//
// 1 <= int(low) <= int(high) < 10¹⁰⁰
// 1 <= low.length, high.length <= 100
// low 和 high 只包含数字。
// low 和 high 都不含前导 0 。
//
//
// Related Topics 字符串 动态规划 👍 10 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
func countSteppingNumbers(low string, high string) int {
	var dfs func(mx string, idx, pre, size int, lt bool) int
	dfs = func(mx string, idx, pre, size int, lt bool) int {
		if idx == size {
			return 1
		}
		if len(mx) > size {
			res := 0
			if idx == 0 {
				for i := 1; i <= 9; i++ {
					res += dfs(mx, idx+1, i, size, true)
				}
			} else {
				if pre+1 <= 9 {
					res += dfs(mx, idx+1, pre+1, size, true)
				}
				if pre-1 >= 0 {
					res += dfs(mx, idx+1, pre-1, size, true)
				}
			}
			return res
		} else {
			res := 0
			if idx == 0 {
				for i := 1; i <= int(mx[0]-'0'); i++ {
					res += dfs(mx, idx+1, i, size, i == int(mx[0]-'0'))
				}
			} else {
				if lt {
					if pre+1 <= 9 {
						res += dfs(mx, idx+1, pre+1, size, true)
					}
					if pre-1 >= 0 {
						res += dfs(mx, idx+1, pre-1, size, true)
					}
				} else {
					if pre-1 >= 0 && pre-1 <= int(mx[idx]-'0') {
						res += dfs(mx, idx+1, pre-1, size, pre-1 < int(mx[idx]-'0'))
					} else {
						// 不满足条件，退出
						return 0
					}
				}
			}
			return res
		}
	}
	cal := func(mx string) int {
		res := 0
		for i := 1; i <= len(mx); i++ {
			res += dfs(mx, 0, 0, i, false)
		}
		return res
	}
	check := func(s string) bool {
		for i := 1; i < len(s); i++ {
			if s[i] != s[i-1]-1 && s[i] != s[i+1]-1 {
				return false
			}
		}
		return true
	}
	res := cal(high) - cal(low)
	if check(low) {
		res++
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)
