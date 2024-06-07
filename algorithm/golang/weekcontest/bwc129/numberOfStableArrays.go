package bwc129

// 给你 3 个正整数 zero ，one 和 limit 。
//
//一个
//二进制数组
// arr 如果满足以下条件，那么我们称它是 稳定的 ：
//
//0 在 arr 中出现次数 恰好 为 zero 。
//1 在 arr 中出现次数 恰好 为 one 。
//arr 中每个长度超过 limit 的
//子数组
// 都 同时 包含 0 和 1 。
//请你返回 稳定 二进制数组的 总 数目。
//
//由于答案可能很大，将它对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//输入：zero = 1, one = 1, limit = 2
//
//输出：2
//
//解释：
//
//两个稳定的二进制数组为 [1,0] 和 [0,1] ，两个数组都有一个 0 和一个 1 ，且没有子数组长度大于 2 。
//
//示例 2：
//
//输入：zero = 1, one = 2, limit = 1
//
//输出：1
//
//解释：
//
//唯一稳定的二进制数组是 [1,0,1] 。
//
//二进制数组 [1,1,0] 和 [0,1,1] 都有长度为 2 且元素全都相同的子数组，所以它们不稳定。
//
//示例 3：
//
//输入：zero = 3, one = 3, limit = 2
//
//输出：14
//
//解释：
//
//所有稳定的二进制数组包括 [0,0,1,0,1,1] ，[0,0,1,1,0,1] ，[0,1,0,0,1,1] ，[0,1,0,1,0,1] ，[0,1,0,1,1,0] ，[0,1,1,0,0,1] ，[0,1,1,0,1,0] ，[1,0,0,1,0,1] ，[1,0,0,1,1,0] ，[1,0,1,0,0,1] ，[1,0,1,0,1,0] ，[1,0,1,1,0,0] ，[1,1,0,0,1,0] 和 [1,1,0,1,0,0] 。
//
//
//
//提示：
//
//1 <= zero, one, limit <= 200

// 这道题还是有点难
// 先定义问题
// f(n,k,l)为长度为n的数据中，有k个1，其中超过l长度的窗口都必然包含0和1（也就是连续0，或者连续1的最大长度为l）
func numberOfStableArrays(zero int, one int, limit int) int {
	dp0, dp1 := make([][][]int, zero+1), make([][][]int, zero+1)
	for i := 0; i <= zero; i++ {
		dp0[i] = make([][]int, one+1)
		dp1[i] = make([][]int, one+1)
		for j := 0; j <= limit; j++ {
			dp0[i][j] = make([]int, limit+1)
			dp1[i][j] = make([]int, limit+1)
		}
	}
	// dp初始化
	for i := 0; i <= min(one, limit); i++ {
		dp0[1][i][1] = 1
	}
	for i := 1; i <= zero; i++ {
		dp0[i][0][i] = 1
	}
	// dp迭代
	for i := 2; i <= zero; i++ {
		for j := 1; j <= one; j++ {
			for k := 1; k <= min(i, limit); k++ {
				for l := 1; l <= min(j, limit); l++ {
					dp0[i][j][k] += dp1[i-k][j][l]
				}
			}
		}
	}
	return 0
}

// 先尝试dfs
func numberOfStableArrays2(zero int, one int, limit int) int {
	// 其中last=0/1
	mod := 1_000_000_007
	var dfs func(num0, num1 int, last int, lastNum int) int
	dfs = func(num0, num1 int, last int, lastNum int) int {
		if num0 == 0 && num1 == 0 {
			return 1
		}
		res := 0
		if last == 0 {
			if num0 > 0 && lastNum < limit {
				res = (res + dfs(num0-1, num1, 0, lastNum+1)) % mod
			}
			if num1 > 0 {
				res = (res + dfs(num0, num1-1, 1, 1)) % mod
			}
		} else {
			if num0 > 0 {
				res = (res + dfs(num0-1, num1, 0, 1)) % mod
			}
			if num1 > 0 && lastNum < limit {
				res = (res + dfs(num0, num1-1, 1, lastNum+1)) % mod
			}
		}
		return res
	}
	return dfs(zero, one, 0, 0)
}

// 上面的解法必然超时，我们需要增加记忆
// 由于zero和one的数据量<200，我们分别需需要8个bit来存储,last为一个bit,lastNum为8个bit
func numberOfStableArrays3(zero int, one int, limit int) int {
	// 其中last=0/1
	mod := 1_000_000_007
	makeKey := func(num0, num1 int, last int, lastNum int) int {
		res := 0
		res |= num0
		res <<= 8
		res |= num1
		res <<= 1
		res |= last
		res <<= 8
		res |= lastNum
		return res
	}
	mem := make(map[int]int)
	var dfs func(num0, num1 int, last int, lastNum int) int
	dfs = func(num0, num1 int, last int, lastNum int) int {
		if num0 == 0 && num1 == 0 {
			return 1
		}
		if v, ok := mem[makeKey(num0, num1, last, lastNum)]; ok {
			return v
		}
		res := 0
		if last == 0 {
			if num0 > 0 && lastNum < limit {
				res = (res + dfs(num0-1, num1, 0, lastNum+1)) % mod
			}
			if num1 > 0 {
				res = (res + dfs(num0, num1-1, 1, 1)) % mod
			}
		} else {
			if num0 > 0 {
				res = (res + dfs(num0-1, num1, 0, 1)) % mod
			}
			if num1 > 0 && lastNum < limit {
				res = (res + dfs(num0, num1-1, 1, lastNum+1)) % mod
			}
		}
		mem[makeKey(num0, num1, last, lastNum)] = res
		return res
	}
	return dfs(zero, one, 0, 0)
}
