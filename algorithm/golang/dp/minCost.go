package dp

import "math"

//假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
//
//当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
//
//例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
//
//请计算出粉刷完所有房子最少的花费成本。
//
//
//
//示例 1：
//
//输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
//输出: 10
//解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
//     最少花费: 2 + 5 + 3 = 10。
//示例 2：
//
//输入: costs = [[7,6,2]]
//输出: 2
//
//
//提示:
//
//costs.length == n
//costs[i].length == 3
//1 <= n <= 100
//1 <= costs[i][j] <= 20
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/JEj789
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func minCost(costs [][]int) int {
	n := len(costs)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 3)
	}
	for i := 0; i < 3; i++ {
		dp[0][i] = costs[0][i]
	}
	for i := 1; i < n; i++ {
		for j := 0; j < 3; j++ {
			dp[i][j] = math.MaxInt32
			for k := 0; k < 3; k++ {
				if j == k {
					continue
				}
				if dp[i-1][k]+costs[i][j] < dp[i][j] {
					dp[i][j] = dp[i-1][k] + costs[i][j]
				}
			}
		}
	}
	res := math.MaxInt32
	for i := 0; i < 3; i++ {
		if dp[n-1][i] < res {
			res = dp[n-1][i]
		}
	}
	return res
}

//空间优化
func minCost2(costs [][]int) int {
	n := len(costs)
	dp := make([]int, 3)
	for i := 0; i < 3; i++ {
		dp[i] = costs[0][i]
	}
	for i := 1; i < n; i++ {
		tmp := make([]int, 3)
		for j := 0; j < 3; j++ {
			tmp[j] = math.MaxInt32
			for k := 0; k < 3; k++ {
				if j == k {
					continue
				}
				if dp[k]+costs[i][j] < tmp[j] {
					tmp[j] = dp[k] + costs[i][j]
				}
			}
		}
		dp = tmp
	}
	res := math.MaxInt32
	for i := 0; i < 3; i++ {
		if dp[i] < res {
			res = dp[i]
		}
	}
	return res
}
