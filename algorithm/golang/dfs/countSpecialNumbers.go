package dfs

import "math"

// 如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
//
//给你一个 正 整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
//
//
//
//示例 1：
//
//输入：n = 20
//输出：19
//解释：1 到 20 之间所有整数除了 11 以外都是特殊整数。所以总共有 19 个特殊整数。
//示例 2：
//
//输入：n = 5
//输出：5
//解释：1 到 5 所有整数都是特殊整数。
//示例 3：
//
//输入：n = 135
//输出：110
//解释：从 1 到 135 总共有 110 个整数是特殊整数。
//不特殊的部分数字为：22 ，114 和 131 。
//
//
//提示：
//
//1 <= n <= 2 * 109

// 居然过了，只是简单的回溯，能不能加点记忆
func countSpecialNumbers(n int) int {
	// idx的范围为[0,9]
	var dfs func(choose int, idx int, num int) int
	dfs = func(choose int, idx int, num int) int {
		if idx == 10 {
			if num == 0 {
				// 0不能算有效答案
				return 0
			} else {
				return 1
			}
		}
		res := 0
		for i := 0; i < 10; i++ {
			// 尝试遍历每一个数字
			if idx == 0 {
				// 防止数字溢出
				if i > 2 {
					break
				}
			}
			if choose&(1<<i) == 0 {
				pow := int(math.Pow10(9 - idx))
				if i*pow+num > n {
					break
				}
				if i == 0 && num == 0 {
					// 前缀0的场景
					res += dfs(choose, idx+1, num+i*pow)
				} else {
					res += dfs(choose|(1<<i), idx+1, num+i*pow)
				}
			}
		}
		return res
	}
	return dfs(0, 0, 0)
}

// 居然过了，只是简单的回溯，能不能加点记忆
func countSpecialNumbers2(n int) int {
	// 把数字的每一位记下来
	arr := make([]int, 10)
	for i := 0; i < 10; i++ {
		pow := int(math.Pow10(9 - i))
		arr[i] = n % pow
		n -= n % pow * pow
	}
	// idx的范围为[0,9],zero判断是否前面都是0，equalBefore判断是否需要判断当前位跟arr对应位的大小
	var dfs func(choose int, idx int, zero bool, equalBefore bool) int
	dfs = func(choose int, idx int, zero bool, equalBefore bool) int {
		if idx == 10 {
			if zero {
				// 0不能算有效答案
				return 0
			} else {
				return 1
			}
		}
		res := 0
		for i := 0; i < 10; i++ {
			// 尝试遍历每一个数字
			if idx == 0 {
				// 防止数字溢出
				if i > 2 {
					break
				}
			}
			if choose&(1<<i) == 0 {
				if equalBefore && i > arr[idx] {
					break
				}
				equalBefore = equalBefore && i == arr[i]
				if i == 0 && zero {
					// 前缀0的场景
					res += dfs(choose, idx+1, true, equalBefore)
				} else {
					res += dfs(choose|(1<<i), idx+1, false, equalBefore)
				}
			}
		}
		return res
	}
	return dfs(0, 0, true, true)
}
