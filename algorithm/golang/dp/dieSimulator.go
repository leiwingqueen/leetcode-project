package dp

// 有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
//
//不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
//
//现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
//
//假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
//
//
//
//示例 1：
//
//输入：n = 2, rollMax = [1,1,2,2,2,3]
//输出：34
//解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
//示例 2：
//
//输入：n = 2, rollMax = [1,1,1,1,1,1]
//输出：30
//示例 3：
//
//输入：n = 3, rollMax = [1,1,1,2,2,3]
//输出：181
//
//
//提示：
//
//1 <= n <= 5000
//rollMax.length == 6
//1 <= rollMax[i] <= 15
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/dice-roll-simulation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// dfs 写法
func dieSimulator(n int, rollMax []int) int {
	mod := 1_000_000_007
	var dfs func(last int, cnt int, rm []int) int
	dfs = func(last int, cnt int, rm []int) int {
		if cnt == 0 {
			return 1
		}
		res := 0
		for i := 0; i < 6; i++ {
			if rm[i] == 0 || i == last {
				continue
			}
			k := rm[i]
			if cnt < k {
				k = cnt
			}
			for j := 1; j <= k; j++ {
				res = (res + dfs(i, cnt-j, rm)) % mod
			}
		}
		return res
	}
	res := dfs(-1, n, rollMax)
	return res
}

func dieSimulator2(n int, rollMax []int) int {
	mod := 1_000_000_007
	dp := make([][]int, 6)
	for i := 0; i < 6; i++ {
		dp[i] = make([]int, n+1)
	}
	for i := 0; i < 6; i++ {
		dp[i][0] = 1
	}
	// f(i,j)=f(k,j)
	for i := 1; i <= n; i++ {
		for j := 0; j < 6; j++ {
			s := 0
			mxK := rollMax[j]
			if i <= mxK {
				// 全选的场景做特殊处理，不能重复计算
				s += 1
				mxK = i - 1
			}
			for l := 0; l < 6; l++ {
				if l == j {
					continue
				}
				for k := 1; k <= mxK; k++ {
					s = (s + dp[l][i-k]) % mod
				}
			}
			dp[j][i] = s
		}
	}
	res := 0
	for i := 0; i < 6; i++ {
		res = (res + dp[i][n]) % mod
	}
	return res
}
