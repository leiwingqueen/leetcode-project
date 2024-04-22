package wc394

// 给你一个大小为 m x n 的二维矩形 grid 。每次 操作 中，你可以将 任一 格子的值修改为 任意 非负整数。完成所有操作后，你需要确保每个格子 grid[i][j] 的值满足：
//
//如果下面相邻格子存在的话，它们的值相等，也就是 grid[i][j] == grid[i + 1][j]（如果存在）。
//如果右边相邻格子存在的话，它们的值不相等，也就是 grid[i][j] != grid[i][j + 1]（如果存在）。
//请你返回需要的 最少 操作数目。

// f(n,k)为前n列，最后一列为k的变更次数
// f(n,k)=min(f(n-1,i)),其中i不等于k
func minimumOperations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	counter := make([][]int, n)
	for i := 0; i < n; i++ {
		counter[i] = make([]int, 10)
	}
	// 统计
	for _, row := range grid {
		for i, num := range row {
			counter[i][num]++
		}
	}
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 10)
	}
	// 初始化
	for i := 0; i < 10; i++ {
		dp[0][i] = m - counter[0][i]
	}
	for i := 1; i < n; i++ {
		for j := 0; j < 10; j++ {
			dp[i][j] = m * n
			for l := 0; l < 10; l++ {
				if j != l {
					dp[i][j] = min(dp[i][j], dp[i-1][l]+m-counter[i][j])
				}
			}
		}
	}
	res := m * n
	for i := 0; i < 10; i++ {
		res = min(dp[n-1][i], res)
	}
	return res
}
