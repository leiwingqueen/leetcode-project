package dp

// 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。你有两名油漆匠：
//
//一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
//一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
//请你返回刷完 n 堵墙最少开销为多少。
//
//
//
//示例 1：
//
//输入：cost = [1,2,3,2], time = [1,2,3,2]
//输出：3
//解释：下标为 0 和 1 的墙由付费油漆匠来刷，需要 3 单位时间。同时，免费油漆匠刷下标为 2 和 3 的墙，需要 2 单位时间，开销为 0 。总开销为 1 + 2 = 3 。
//示例 2：
//
//输入：cost = [2,3,4,2], time = [1,1,1,1]
//输出：4
//解释：下标为 0 和 3 的墙由付费油漆匠来刷，需要 2 单位时间。同时，免费油漆匠刷下标为 1 和 2 的墙，需要 2 单位时间，开销为 0 。总开销为 2 + 2 = 4 。
//
//
//提示：
//
//1 <= cost.length <= 500
//cost.length == time.length
//1 <= cost[i] <= 106
//1 <= time[i] <= 500

// 假设f(i,j,k)为前i个中选择j个，使其耗时之和>=k
// 我们有f(i,j,k)=min(f(i-1,j,k),f(i-1,j-1,k-time[i-1])+cost[i-1])
func paintWalls(cost []int, time []int) int {
	n := len(cost)
	dp := make([][]int, n+1)
	tmp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, n+1)
		tmp[i] = make([]int, n+1)
	}
	mx := 0
	for _, c := range cost {
		mx += c
	}
	for i := 0; i <= n; i++ {
		for j := 0; j <= n; j++ {
			dp[i][j] = mx
		}
	}
	dp[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j <= n; j++ {
			for k := 0; k <= n; k++ {
				if j > i {
					tmp[j][k] = mx
				} else if k == 0 {
					tmp[j][k] = 0
				} else if j == 0 {
					tmp[j][k] = mx
				} else {
					tmp[j][k] = mx
					if j > i {
						tmp[j][k] = dp[j][k]
					}
					tmp[j][k] = min(tmp[j][k], dp[j-1][max(k-time[i-1], 0)]+cost[i-1])
				}
			}
		}
		for j := 0; j <= n; j++ {
			copy(dp[j], tmp[j])
		}
	}
	res := mx
	for i := 1; i <= n; i++ {
		res = min(res, dp[i][n-i])
	}
	return res
}
