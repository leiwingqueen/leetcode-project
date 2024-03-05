package graph

import "math"

// 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
//
//给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
//
//请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//
//输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
//输出：4
//解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
//四条花费 7 分钟的路径分别为：
//- 0 ➝ 6
//- 0 ➝ 4 ➝ 6
//- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
//- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
//示例 2：
//
//输入：n = 2, roads = [[1,0,10]]
//输出：1
//解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
//
//
//提示：
//
//1 <= n <= 200
//n - 1 <= roads.length <= n * (n - 1) / 2
//roads[i].length == 3
//0 <= ui, vi <= n - 1
//1 <= timei <= 109
//ui != vi
//任意两个路口之间至多有一条路。
//从任意路口出发，你能够到达其他任意路口。

// 超时
func countPaths(n int, roads [][]int) int {
	//floyd算法计算最短路径
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	dis := make([][]int, n)
	for i := 0; i < n; i++ {
		dis[i] = make([]int, n)
		for j := 0; j < n; j++ {
			dis[i][j] = math.MaxInt / 2
		}
		dis[i][i] = 0
	}
	for _, edge := range roads {
		x, y, w := edge[0], edge[1], edge[2]
		dis[x][y] = w
		dis[y][x] = w
	}
	for k := 0; k < n; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				dis[i][j] = min(dis[i][j], dis[i][k]+dis[k][j])
			}
		}
	}
	// 最短路径
	k := dis[0][n-1]
	graph := make([][]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make([]int, n)
		for j := 0; j < n; j++ {
			graph[i][j] = math.MaxInt / 2
		}
	}
	for _, road := range roads {
		u, v, w := road[0], road[1], road[2]
		graph[u][v] = w
		graph[v][u] = w
	}
	dp := make([][]int, k+1)
	for i := 0; i <= k; i++ {
		dp[i] = make([]int, n)
	}
	dp[0][0] = 1
	for i := 1; i <= k; i++ {
		for j := 1; j < n; j++ {
			for l := 0; l < n; l++ {
				if graph[j][l] <= i {
					dp[i][j] += dp[i-graph[j][l]][l]
				}
			}
		}
	}
	return dp[k][n-1]
}
