package dp

import "math"

// 给你两个 正 整数 n 和 x 。
//
//请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。换句话说，你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1x + n2x + ... + nkx 。
//
//由于答案可能非常大，请你将它对 109 + 7 取余后返回。
//
//比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 23 + 33 + 53 。
//
//
//
//示例 1：
//
//输入：n = 10, x = 2
//输出：1
//解释：我们可以将 n 表示为：n = 32 + 12 = 10 。
//这是唯一将 10 表达成不同整数 2 次方之和的方案。
//示例 2：
//
//输入：n = 4, x = 1
//输出：2
//解释：我们可以将 n 按以下方案表示：
//- n = 41 = 4 。
//- n = 31 + 11 = 4 。
//
//
//提示：
//
//1 <= n <= 300
//1 <= x <= 5

// 没什么思路，先尝试用回溯法来解决
func numberOfWays2(n int, x int) int {
	// 快速幂
	var pow func(i, j int) int
	pow = func(i, j int) int {
		if j == 0 {
			return 1
		}
		if j%2 == 0 {
			num := pow(i, j/2)
			return num * num
		} else {
			num := pow(i, (j-1)/2)
			return num * num * i
		}
	}
	var dfs func(expect int, mx int) int
	dfs = func(expect int, mx int) int {
		if expect < 0 {
			return 0
		}
		if expect == 0 {
			return 1
		}
		if mx <= 0 {
			return 0
		}
		// 一个个数字尝试
		res := 0
		for i := 1; i <= mx; i++ {
			p := pow(i, x)
			if p > expect {
				break
			}
			res += dfs(expect-p, i-1)
		}
		return res
	}
	return dfs(n, n)
}

// 简单增加记忆，勉强通过
func numberOfWays3(n int, x int) int {
	mod := 1_000_000_007
	// 快速幂
	var pow func(i, j int) int
	pow = func(i, j int) int {
		if j == 0 {
			return 1
		}
		if j%2 == 0 {
			num := pow(i, j/2)
			return num * num
		} else {
			num := pow(i, (j-1)/2)
			return num * num * i
		}
	}
	mem := make(map[int]int)
	buildKey := func(a, b int) int {
		return a<<9 | b
	}
	var dfs func(expect int, mx int) int
	dfs = func(expect int, mx int) int {
		if expect < 0 {
			return 0
		}
		if expect == 0 {
			return 1
		}
		if mx <= 0 {
			return 0
		}
		key := buildKey(expect, mx)
		if v, ok := mem[key]; ok {
			return v
		}
		// 一个个数字尝试
		res := 0
		for i := 1; i <= mx; i++ {
			p := pow(i, x)
			if p > expect {
				break
			}
			res = (res + dfs(expect-p, i-1)) % mod
		}
		mem[key] = res
		return res
	}
	return dfs(n, n)
}

// 改成dp的写法
func numberOfWays4(n int, x int) int {
	mod := 1_000_000_007
	// 快速幂
	var pow func(i, j int) int
	pow = func(i, j int) int {
		if j == 0 {
			return 1
		}
		if j%2 == 0 {
			num := pow(i, j/2)
			return num * num
		} else {
			num := pow(i, (j-1)/2)
			return num * num * i
		}
	}
	// 计算最大的底数，减少运算量
	base := int(math.Pow(float64(n), 1/float64(x))) + 1
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, base+1)
	}
	// dp初始化
	for i := 0; i <= base; i++ {
		dp[0][i] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= base; j++ {
			dp[i][j] = dp[i][j-1]
			p := pow(j, x)
			if p <= i {
				dp[i][j] = (dp[i][j] + dp[i-p][j-1]) % mod
			}
		}
	}
	return dp[n][base]
}
