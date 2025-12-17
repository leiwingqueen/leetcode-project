package dp

// 给你一个整数数组 prices，其中 prices[i] 是第 i 天股票的价格（美元），以及一个整数 k。
//
//你最多可以进行 k 笔交易，每笔交易可以是以下任一类型：
//
//普通交易：在第 i 天买入，然后在之后的第 j 天卖出，其中 i < j。你的利润是 prices[j] - prices[i]。
//
//做空交易：在第 i 天卖出，然后在之后的第 j 天买回，其中 i < j。你的利润是 prices[i] - prices[j]。
//
//注意：你必须在开始下一笔交易之前完成当前交易。此外，你不能在已经进行买入或卖出操作的同一天再次进行买入或卖出操作。
//
//通过进行 最多 k 笔交易，返回你可以获得的最大总利润。
//
//
//
//示例 1:
//
//输入: prices = [1,7,9,8,2], k = 2
//
//输出: 14
//
//解释:
//
//我们可以通过 2 笔交易获得 14 美元的利润：
//一笔普通交易：第 0 天以 1 美元买入，第 2 天以 9 美元卖出。
//一笔做空交易：第 3 天以 8 美元卖出，第 4 天以 2 美元买回。
//示例 2:
//
//输入: prices = [12,16,19,19,8,1,19,13,9], k = 3
//
//输出: 36
//
//解释:
//
//我们可以通过 3 笔交易获得 36 美元的利润：
//一笔普通交易：第 0 天以 12 美元买入，第 2 天以 19 美元卖出。
//一笔做空交易：第 3 天以 19 美元卖出，第 4 天以 8 美元买回。
//一笔普通交易：第 5 天以 1 美元买入，第 6 天以 19 美元卖出。
//
//
//提示:
//
//2 <= prices.length <= 103
//1 <= prices[i] <= 109
//1 <= k <= prices.length / 2

// 朴素的DP写法，超时
func maximumProfit(prices []int, k int) int64 {
	n := len(prices)
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	dp := make([][]int64, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int64, k+1)
	}
	for i := 2; i <= n; i++ {
		for j := 1; j <= k; j++ {
			dp[i][j] = dp[i-1][j]
			// 选择长度为l作为购买的周期长度
			for l := 2; l <= i; l++ {
				dp[i][j] = max(dp[i][j], int64(abs(prices[i-1]-prices[i-l]))+dp[i-l][j-1])
			}
		}
	}
	return dp[n][k]
}

// 需要区分两种状态，持有股票（做空 or 做多）或者没有持有股票
// dp0,dp1,dp2分别是没有持有，持有一只做多的股票，持有一只做空的股票
// dp0(i,j)= max(dp0(i-1,j),dp1(i-1,j)+prices[i-1],dp2(i-1,j)-prices[i-1])
// dp1(i,j)= max(dp1(i-1,j),dp0(i,j-1)-prices[i-1])
// dp2(i,j)= max(dp2(i-1,j),dp0(i,j-1)+prices[i-1])
func maximumProfit2(prices []int, k int) int64 {
	n := len(prices)
	dp0, dp1, dp2 := make([]int64, k+1), make([]int64, k+1), make([]int64, k+1)
	tmp0, tmp1, tmp2 := make([]int64, k+1), make([]int64, k+1), make([]int64, k+1)
	for i := 1; i <= k; i++ {
		tmp1[i] = -int64(prices[0])
		tmp2[i] = int64(prices[0])
	}
	for i := 1; i <= n; i++ {
		dp1[0] = max(tmp1[0], tmp0[0]-int64(prices[i-1]))
		dp2[0] = max(tmp2[0], tmp0[0]+int64(prices[i-1]))
		for j := 1; j <= k; j++ {
			dp0[j] = max(tmp0[j], tmp1[j]+int64(prices[i-1]), tmp2[j]-int64(prices[i-1]))
			dp1[j] = max(tmp1[j], tmp0[j-1]-int64(prices[i-1]))
			dp2[j] = max(tmp2[j], tmp0[j-1]+int64(prices[i-1]))
		}
		copy(tmp0, dp0)
		copy(tmp1, dp1)
		copy(tmp2, dp2)
	}
	return dp0[k]
}
