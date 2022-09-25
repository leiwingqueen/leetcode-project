package wc312

func numberOfGoodPaths(vals []int, edges [][]int) int {
	n := len(vals)
	graph := make([][]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make([]int, 0)
	}
	for _, edge := range edges {
		graph[edge[0]] = append(graph[edge[0]], edge[1])
		graph[edge[1]] = append(graph[edge[1]], edge[0])
	}
	var dfs func(start int, cur int, visit []bool) int
	dfs = func(start int, cur int, visit []bool) int {
		r := 0
		if vals[cur] == vals[start] && start != cur {
			r++
		}
		for _, next := range graph[cur] {
			if !visit[next] && vals[next] <= vals[start] {
				visit[next] = true
				r += dfs(start, next, visit)
				visit[next] = false
			}
		}
		return r
	}
	res := 0
	for i := 0; i < n; i++ {
		visit := make([]bool, n)
		visit[i] = true
		res += dfs(i, i, visit)
	}
	return res/2 + n
}
