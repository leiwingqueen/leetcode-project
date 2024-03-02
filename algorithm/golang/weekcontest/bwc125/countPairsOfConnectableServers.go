package bwc125

func countPairsOfConnectableServers(edges [][]int, signalSpeed int) []int {
	n := len(edges) + 1
	graph := make([][][]int, n)
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		graph[x] = append(graph[x], []int{y, w})
		graph[y] = append(graph[y], []int{x, w})
	}
	var dfs func(node, parent int, cost int) int
	dfs = func(node, parent int, cost int) int {
		cnt := 0
		if cost%signalSpeed == 0 {
			cnt++
		}
		for _, next := range graph[node] {
			y, w := next[0], next[1]
			if y != parent {
				cnt += dfs(y, node, cost+w)
			}
		}
		return cnt
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		sum := 0
		for _, next := range graph[i] {
			y, w := next[0], next[1]
			cnt := dfs(y, i, w)
			res[i] += sum * cnt
			sum += cnt
		}
	}
	return res
}
