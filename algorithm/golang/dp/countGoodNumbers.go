package dp

import "math"

// 我们称一个数字字符串是 好数字 当它满足（下标从 0 开始）偶数 下标处的数字为 偶数 且 奇数 下标处的数字为 质数 （2，3，5 或 7）。
//
//比方说，"2582" 是好数字，因为偶数下标处的数字（2 和 8）是偶数且奇数下标处的数字（5 和 2）为质数。但 "3245" 不是 好数字，因为 3 在偶数下标处但不是偶数。
//给你一个整数 n ，请你返回长度为 n 且为好数字的数字字符串 总数 。由于答案可能会很大，请你将它对 109 + 7 取余后返回 。
//
//一个 数字字符串 是每一位都由 0 到 9 组成的字符串，且可能包含前导 0 。
//
//
//
//示例 1：
//
//输入：n = 1
//输出：5
//解释：长度为 1 的好数字包括 "0"，"2"，"4"，"6"，"8" 。
//示例 2：
//
//输入：n = 4
//输出：400
//示例 3：
//
//输入：n = 50
//输出：564908303
//
//
//提示：
//
//1 <= n <= 1015

// 典型的DP，但是必然超时
func countGoodNumbers(n int64) int {
	mod := 1_000_000_007
	dp := make([]int, n)
	dp[0] = 5
	for i := int64(1); i < n; i++ {
		if (i+1)%2 == 0 {
			dp[i] = (dp[i-1] * 4) % mod
		} else {
			dp[i] = (dp[i-1] * 5) % mod
		}
	}
	return dp[n-1]
}

// 优化一下
func countGoodNumbers2(n int64) int {
	mod := 1_000_000_007
	fn := 5
	for i := int64(1); i < n; i++ {
		// 这里我们留意一下，这里是有规律的
		if (i+1)%2 == 0 {
			fn = (fn * 4) % mod
		} else {
			fn = (fn * 5) % mod
		}
	}
	return fn
}

// 在上面基础上再优化一下
func countGoodNumbers3(n int64) int {
	mod := 1_000_000_007
	fn := 1
	for i := int64(0); i < n; i++ {
		// 这里我们留意一下
		if i%2 == 1 {
			fn = (fn * 4) % mod
		} else {
			fn = (fn * 5) % mod
		}
	}
	return fn
}

func countGoodNumbers4(n int64) int {
	mod := int64(1_000_000_007)
	pow1 := int64(math.Pow(float64(5), float64((n+1)/2))) % mod
	pow2 := int64(math.Pow(float64(4), float64(n/2))) % mod
	return int((pow1 * pow2) % mod)
}

// 其实就是需要用到快速幂
func countGoodNumbers5(n int64) int {
	mod := 1_000_000_007
	var quickMul func(x int, y int64) int
	quickMul = func(x int, y int64) int {
		if y == 0 {
			return 1
		}
		t := quickMul(x, y/2)
		res := t * t % mod
		if y%2 == 1 {
			res = res * x % mod
		}
		return res
	}
	return quickMul(5, (n+1)/2) * quickMul(4, n/2) % mod
}
