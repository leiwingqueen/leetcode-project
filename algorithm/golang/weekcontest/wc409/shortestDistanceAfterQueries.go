package wc409

// 给你一个整数 n 和一个二维整数数组 queries。
//
//有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
//
//queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，你需要找到从城市 0 到城市 n - 1 的最短路径的长度。
//
//返回一个数组 answer，对于范围 [0, queries.length - 1] 中的每个 i，answer[i] 是处理完前 i + 1 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
//
//
//
//示例 1：
//
//输入： n = 5, queries = [[2, 4], [0, 2], [0, 4]]
//
//输出： [3, 2, 1]
//
//解释：
//
//
//
//新增一条从 2 到 4 的道路后，从 0 到 4 的最短路径长度为 3。
//
//
//
//新增一条从 0 到 2 的道路后，从 0 到 4 的最短路径长度为 2。
//
//
//
//新增一条从 0 到 4 的道路后，从 0 到 4 的最短路径长度为 1。
//
//示例 2：
//
//输入： n = 4, queries = [[0, 3], [0, 2]]
//
//输出： [1, 1]
//
//解释：
//
//
//
//新增一条从 0 到 3 的道路后，从 0 到 3 的最短路径长度为 1。
//
//
//
//新增一条从 0 到 2 的道路后，从 0 到 3 的最短路径长度仍为 1。
//
//
//
//提示：
//
//3 <= n <= 500
//1 <= queries.length <= 500
//queries[i].length == 2
//0 <= queries[i][0] < queries[i][1] < n
//1 < queries[i][1] - queries[i][0]
//查询中没有重复的道路。

// 超时，时间复杂度O(n^3)
func shortestDistanceAfterQueries(n int, queries [][]int) []int {
	INF := n
	dis := make([][]int, n)
	for i := 0; i < n; i++ {
		dis[i] = make([]int, n)
		for j := 0; j < n; j++ {
			dis[i][j] = INF
		}
	}
	// 路径初始化
	for i := 0; i < n; i++ {
		dis[i][i] = 0
	}
	// floyd算法
	for i := 1; i < n; i++ {
		// 添加[i-1,i]的路径
		for from := 0; from < n; from++ {
			for end := 0; end < n; end++ {
				dis[from][end] = min(dis[from][i-1]+dis[i][end]+1, dis[from][end])
			}
		}
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		x, y := query[0], query[1]
		for from := 0; from < n; from++ {
			for end := 0; end < n; end++ {
				dis[from][end] = min(dis[from][x]+dis[y][end]+1, dis[from][end])
			}
		}
		res[i] = dis[0][n-1]
	}
	return res
}

// 还不如用BFS
func shortestDistanceAfterQueries2(n int, queries [][]int) []int {
	graph := make([][]int, n)
	for i := 1; i < n; i++ {
		graph[i-1] = append(graph[i-1], i)
	}
	bfs := func() int {
		queue := []int{0}
		visit := make([]bool, n)
		visit[0] = true
		depth := 0
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				node := queue[i]
				if node == n-1 {
					return depth
				}
				for _, next := range graph[node] {
					if !visit[next] {
						visit[next] = true
						queue = append(queue, next)
					}
				}
			}
			queue = queue[size:]
			depth++
		}
		return -1
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		x, y := query[0], query[1]
		graph[x] = append(graph[x], y)
		res[i] = bfs()
	}
	return res
}
