package dp

// 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
//
//
//
//示例 1：
//
//输入：matrix =
//[
//  [0,1,1,1],
//  [1,1,1,1],
//  [0,1,1,1]
//]
//输出：15
//解释：
//边长为 1 的正方形有 10 个。
//边长为 2 的正方形有 4 个。
//边长为 3 的正方形有 1 个。
//正方形的总数 = 10 + 4 + 1 = 15.
//示例 2：
//
//输入：matrix =
//[
//  [1,0,1],
//  [1,1,0],
//  [1,1,0]
//]
//输出：7
//解释：
//边长为 1 的正方形有 6 个。
//边长为 2 的正方形有 1 个。
//正方形的总数 = 6 + 1 = 7.
//
//
//提示：
//
//1 <= arr.length <= 300
//1 <= arr[0].length <= 300
//0 <= arr[i][j] <= 1

// f(i,j)=min{f(i-1,j),f(i,j-1),f(i-1,j-1)}+1
func countSquares(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// dp初始化
	res := 0
	for i := 0; i < m; i++ {
		dp[i][0] = matrix[i][0]
		res += dp[i][0]
	}
	for i := 1; i < n; i++ {
		dp[0][i] = matrix[0][i]
		res += dp[0][i]
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if matrix[i][j] == 1 {
				dp[i][j] = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) + 1
				res += dp[i][j]
			}
		}
	}
	return res
}
