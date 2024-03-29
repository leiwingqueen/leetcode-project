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
// 先不考虑溢出的问题
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
					res += dfs(mx, idx+1, i, size, i != int(mx[0]-'0'))
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
					flag := false
					if pre-1 >= 0 && pre-1 <= int(mx[idx]-'0') {
						res += dfs(mx, idx+1, pre-1, size, pre-1 < int(mx[idx]-'0'))
						flag = true
					}
					if pre+1 <= 9 && pre+1 <= int(mx[idx]-'0') {
						res += dfs(mx, idx+1, pre+1, size, pre+1 < int(mx[idx]-'0'))
						flag = true
					}
					if !flag {
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
			if s[i] != s[i-1]-1 && s[i] != s[i-1]+1 {
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

// 超时
func countSteppingNumbers2(low string, high string) int {
	mod := 1_000_000_007
	var dfs func(mx string, idx, pre, size int, lt bool) int
	dfs = func(mx string, idx, pre, size int, lt bool) int {
		if idx == size {
			return 1
		}
		if len(mx) > size {
			res := 0
			if idx == 0 {
				for i := 1; i <= 9; i++ {
					res = (res + dfs(mx, idx+1, i, size, true)) % mod
				}
			} else {
				if pre+1 <= 9 {
					res = (res + dfs(mx, idx+1, pre+1, size, true)) % mod
				}
				if pre-1 >= 0 {
					res = (res + dfs(mx, idx+1, pre-1, size, true)) % mod
				}
			}
			return res
		} else {
			res := 0
			if idx == 0 {
				for i := 1; i <= int(mx[0]-'0'); i++ {
					res = (res + dfs(mx, idx+1, i, size, i != int(mx[0]-'0'))) % mod
				}
			} else {
				if lt {
					if pre+1 <= 9 {
						res = (res + dfs(mx, idx+1, pre+1, size, true)) % mod
					}
					if pre-1 >= 0 {
						res = (res + dfs(mx, idx+1, pre-1, size, true)) % mod
					}
				} else {
					flag := false
					if pre-1 >= 0 && pre-1 <= int(mx[idx]-'0') {
						res = (res + dfs(mx, idx+1, pre-1, size, pre-1 < int(mx[idx]-'0'))) % mod
						flag = true
					}
					if pre+1 <= 9 && pre+1 <= int(mx[idx]-'0') {
						res = (res + dfs(mx, idx+1, pre+1, size, pre+1 < int(mx[idx]-'0'))) % mod
						flag = true
					}
					if !flag {
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
			res = (res + dfs(mx, 0, 0, i, false)) % mod
		}
		return res
	}
	check := func(s string) bool {
		for i := 1; i < len(s); i++ {
			if s[i] != s[i-1]-1 && s[i] != s[i-1]+1 {
				return false
			}
		}
		return true
	}
	res := (cal(high) + mod - cal(low)) % mod
	if check(low) {
		res = (res + 1) % mod
	}
	return res
}

// dp解法
func countSteppingNumbers3(low string, high string) int {
	n := len(high)
	// fn(k,i)为以i开头，长度为k的步进数字数量
	fn := make([][]int, n)
	for i := 0; i < n; i++ {
		fn[i] = make([]int, 10)
	}
	// 初始化
	for i := 0; i < 10; i++ {
		fn[0][i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < 10; j++ {
			if j > 0 {
				fn[i][j] += fn[i-1][j-1]
			}
			if j < 9 {
				fn[i][j] += fn[i-1][j+1]
			}
		}
	}
	var dfs func(mx string, idx, pre int) int
	dfs = func(mx string, idx, pre int) int {
		k := len(mx)
		if idx == k {
			return 1
		}
		res := 0
		if idx == 0 {
			for i := 1; i < int(mx[0]-'0'); i++ {
				res += fn[k-idx-1][i]
			}
			res += dfs(mx, idx+1, int(mx[0]-'0'))
		} else {
			flag := false
			cur := int(mx[idx] - '0')
			if pre > 0 && pre-1 <= cur {
				if pre-1 == cur {
					res += dfs(mx, idx+1, pre-1)
				} else {
					res += fn[k-idx-1][pre-1]
				}
				flag = true
			}
			if pre < 9 && pre+1 <= cur {
				if pre+1 == cur {
					res += dfs(mx, idx+1, pre+1)
				} else {
					res += fn[k-idx-1][pre+1]
				}
				flag = true
			}
			if !flag {
				return 0
			}
		}
		return res
	}
	cal := func(mx string) int {
		res := 0
		for i := 1; i < len(mx); i++ {
			for j := 1; j < 10; j++ {
				res += fn[i-1][j]
			}
		}
		res += dfs(mx, 0, 0)
		return res
	}
	check := func(s string) bool {
		for i := 1; i < len(s); i++ {
			if s[i] != s[i-1]-1 && s[i] != s[i-1]+1 {
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

// 在上面基础上再对结果进行取模
func countSteppingNumbers4(low string, high string) int {
	mod := 1_000_000_007
	n := len(high)
	// fn(k,i)为以i开头，长度为k的步进数字数量
	fn := make([][]int, n)
	for i := 0; i < n; i++ {
		fn[i] = make([]int, 10)
	}
	// 初始化
	for i := 0; i < 10; i++ {
		fn[0][i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < 10; j++ {
			if j > 0 {
				fn[i][j] = (fn[i][j] + fn[i-1][j-1]) % mod
			}
			if j < 9 {
				fn[i][j] = (fn[i][j] + fn[i-1][j+1]) % mod
			}
		}
	}
	var dfs func(mx string, idx, pre int) int
	dfs = func(mx string, idx, pre int) int {
		k := len(mx)
		if idx == k {
			return 1
		}
		res := 0
		if idx == 0 {
			for i := 1; i < int(mx[0]-'0'); i++ {
				res = (res + fn[k-idx-1][i]) % mod
			}
			res = (res + dfs(mx, idx+1, int(mx[0]-'0'))) % mod
		} else {
			flag := false
			cur := int(mx[idx] - '0')
			if pre > 0 && pre-1 <= cur {
				if pre-1 == cur {
					res = (res + dfs(mx, idx+1, pre-1)) % mod
				} else {
					res = (res + fn[k-idx-1][pre-1]) % mod
				}
				flag = true
			}
			if pre < 9 && pre+1 <= cur {
				if pre+1 == cur {
					res = (res + dfs(mx, idx+1, pre+1)) % mod
				} else {
					res = (res + fn[k-idx-1][pre+1]) % mod
				}
				flag = true
			}
			if !flag {
				return 0
			}
		}
		return res
	}
	cal := func(mx string) int {
		res := 0
		for i := 1; i < len(mx); i++ {
			for j := 1; j < 10; j++ {
				res = (res + fn[i-1][j]) % mod
			}
		}
		res = (res + dfs(mx, 0, 0)) % mod
		return res
	}
	check := func(s string) bool {
		for i := 1; i < len(s); i++ {
			if s[i] != s[i-1]-1 && s[i] != s[i-1]+1 {
				return false
			}
		}
		return true
	}
	res := (cal(high) + mod - cal(low)) % mod
	if check(low) {
		res = (res + 1) % mod
	}
	return res
}
