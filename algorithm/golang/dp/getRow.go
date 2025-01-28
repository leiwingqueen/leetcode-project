package dp

// 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
//
// 示例 1:
//
// 输入: rowIndex = 3
// 输出: [1,3,3,1]
// 示例 2:
//
// 输入: rowIndex = 0
// 输出: [1]
// 示例 3:
//
// 输入: rowIndex = 1
// 输出: [1,1]
//
// 提示:
//
// 0 <= rowIndex <= 33
//
// 进阶：
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？

// 简单实现
func getRow(rowIndex int) []int {
	dp := make([][]int, rowIndex+1)
	// 初始化
	for i := 0; i <= rowIndex; i++ {
		dp[i] = make([]int, i+1)
		dp[i][0] = 1
		dp[i][i] = 1
	}
	for i := 2; i <= rowIndex; i++ {
		for j := 1; j < i; j++ {
			dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
		}
	}
	return dp[rowIndex]
}

// 空间压缩
func getRow2(rowIndex int) []int {
	if rowIndex == 0 {
		return []int{1}
	}
	dp, tmp := make([]int, rowIndex+1), make([]int, rowIndex+1)
	dp[0] = 1
	for i := 1; i <= rowIndex; i++ {
		tmp[0] = 1
		tmp[i] = 1
		for j := 1; j < i; j++ {
			tmp[j] = dp[j-1] + dp[j]
		}
		copy(dp, tmp)
	}
	return dp
}
