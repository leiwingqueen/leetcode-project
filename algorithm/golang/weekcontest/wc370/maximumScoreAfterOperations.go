package wc370

// 增加记忆
func maximumScoreAfterOperations(edges [][]int, values []int) int64 {
	n := len(values)
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	dp := make([][]int64, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int64, 2)
		for j := 0; j < 2; j++ {
			dp[i][j] = -1
		}
	}
	var dfs func(node, parent int, flag int) int64
	dfs = func(node, parent int, flag int) int64 {
		if dp[node][flag] >= 0 {
			return dp[node][flag]
		}
		var res int64
		defer func() {
			dp[node][flag] = res
		}()
		if flag == 1 {
			res = int64(values[node])
			for _, next := range graph[node] {
				if next != parent {
					res += dfs(next, node, 1)
				}
			}
			return res
		} else {
			r1, r2 := int64(0), int64(values[node])
			cnt := 0
			for _, next := range graph[node] {
				if next != parent {
					r1 += dfs(next, node, 1)
					r2 += dfs(next, node, 0)
					cnt++
				}
			}
			if cnt == 0 {
				res = 0
			} else {
				if r1 > r2 {
					res = r1
				} else {
					res = r2
				}
			}
			return res
		}
	}
	return dfs(0, -1, 0)
}
