package dp

// 给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1 次，得到 k 块披萨并送给别人。
//
//切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。
//
//请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。
//
//
//
//示例 1：
//
//
//
//输入：pizza = ["A..","AAA","..."], k = 3
//输出：3
//解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
//示例 2：
//
//输入：pizza = ["A..","AA.","..."], k = 3
//输出：1
//示例 3：
//
//输入：pizza = ["A..","A..","..."], k = 1
//输出：1
//
//
//提示：
//
//1 <= rows, cols <= 50
//rows == pizza.length
//cols == pizza[i].length
//1 <= k <= 10
//pizza 只包含字符 'A' 和 '.' 。

func ways(pizza []string, k int) int {
	m, n := len(pizza), len(pizza[0])
	// dp[k][m][n]
	dp := make([][][]int, k)
	for i := 0; i < k; i++ {
		dp[i] = make([][]int, m)
		for j := 0; j < m; j++ {
			dp[i][j] = make([]int, n)
		}
	}
	// 统计左上角为[i,j]的苹果数量
	preSum := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		preSum[i] = make([]int, n+1)
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			preSum[i][j] = preSum[i][j+1] + preSum[i+1][j] - preSum[i+1][j+1]
			if pizza[i][j] == 'A' {
				preSum[i][j]++
			}
		}
	}
	// 初始化
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if preSum[i][j] >= 1 {
				dp[0][i][j] = 1
			}
		}
	}
	// dp迭代
	for l := 1; l < k; l++ {
		for i := m - 1; i >= 0; i-- {
			for j := n - 1; j >= 0; j-- {
				if preSum[i][j] < l+1 {
					continue
				}
				// 横切的场景
				for r := i + 1; r < m; r++ {
					// 分成两部分[i,j]->[r,j]
					if preSum[r][j] < l {
						break
					}
					if preSum[i][j]-preSum[r][j] > 0 {
						// 至少要分一个
						dp[l][i][j] += dp[l-1][r][j]
					}
				}
				// 竖切的场景
				for c := j + 1; c < n; c++ {
					if preSum[i][c] < l {
						break
					}
					if preSum[i][j]-preSum[i][c] > 0 {
						dp[l][i][j] += dp[l-1][i][c]
					}
				}
			}
		}
	}
	return dp[k-1][m-1][n-1]
}
