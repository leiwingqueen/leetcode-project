package wc463

// 给你两个整数数组 prices 和 strategy，其中：
//
//prices[i] 表示第 i 天某股票的价格。
//strategy[i] 表示第 i 天的交易策略，其中：
//-1 表示买入一单位股票。
//0 表示持有股票。
//1 表示卖出一单位股票。
//同时给你一个 偶数 整数 k，你可以对 strategy 进行 最多一次 修改。一次修改包括：
//
//选择 strategy 中恰好 k 个 连续 元素。
//将前 k / 2 个元素设为 0（持有）。
//将后 k / 2 个元素设为 1（卖出）。
//利润 定义为所有天数中 strategy[i] * prices[i] 的 总和 。
//
//返回你可以获得的 最大 可能利润。
//
//注意： 没有预算或股票持有数量的限制，因此所有买入和卖出操作均可行，无需考虑过去的操作。
//
//
//
//示例 1：
//
//输入： prices = [4,2,8], strategy = [-1,0,1], k = 2
//
//输出： 10
//
//解释：
//
//修改	策略	利润计算	利润
//原始	[-1, 0, 1]	(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8	4
//修改 [0, 1]	[0, 1, 1]	(0 × 4) + (1 × 2) + (1 × 8) = 0 + 2 + 8	10
//修改 [1, 2]	[-1, 0, 1]	(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8	4
//因此，最大可能利润是 10，通过修改子数组 [0, 1] 实现。
//
//示例 2：
//
//输入： prices = [5,4,3], strategy = [1,1,0], k = 2
//
//输出： 9
//
//解释：
//
//修改	策略	利润计算	利润
//原始	[1, 1, 0]	(1 × 5) + (1 × 4) + (0 × 3) = 5 + 4 + 0	9
//修改 [0, 1]	[0, 1, 0]	(0 × 5) + (1 × 4) + (0 × 3) = 0 + 4 + 0	4
//修改 [1, 2]	[1, 0, 1]	(1 × 5) + (0 × 4) + (1 × 3) = 5 + 0 + 3	8
//因此，最大可能利润是 9，无需任何修改即可达成。
//
//
//
//提示：
//
//2 <= prices.length == strategy.length <= 105
//1 <= prices[i] <= 105
//-1 <= strategy[i] <= 1
//2 <= k <= prices.length
//k 是偶数

// 其实就是计算滑动窗口内的值哪个值是最大的
func maxProfit(prices []int, strategy []int, k int) int64 {
	n := len(prices)
	// 先计算没有任何修改的情况下的最大值
	var total int64
	for i := 0; i < n; i++ {
		p := prices[i]
		s := strategy[i]
		total += int64(s) * int64(p)
	}
	l, r := 0, 0
	var diff, maxDiff int64
	// 先初始化窗口
	for i := 0; i < k/2; i++ {
		diff += int64(0-strategy[i]) * int64(prices[i])
	}
	for i := k / 2; i < k; i++ {
		diff += int64(1-strategy[i]) * int64(prices[i])
	}
	maxDiff = max(0, diff)
	r = k
	// 窗口滑动
	for r < n {
		// 拓展窗口
		diff += int64(1-strategy[r]) * int64(prices[r])
		r++
		diff -= int64(0-strategy[l]) * int64(prices[l])
		l++
		mid := l + k/2 - 1
		// 从1变成0
		diff += int64(-1) * int64(prices[mid])
		maxDiff = max(maxDiff, diff)
	}
	return total + maxDiff
}
