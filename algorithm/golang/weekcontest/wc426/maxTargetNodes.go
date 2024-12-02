package wc426

func maxTargetNodes(edges1 [][]int, edges2 [][]int, k int) []int {
	n, m := len(edges1)+1, len(edges2)+1
	graph1, graph2 := make([][]int, n), make([][]int, m)
	for _, edge := range edges1 {
		x, y := edge[0], edge[1]
		graph1[x] = append(graph1[x], y)
		graph1[y] = append(graph1[y], x)
	}
	for _, edge := range edges2 {
		x, y := edge[0], edge[1]
		graph2[x] = append(graph2[x], y)
		graph2[y] = append(graph2[y], x)
	}
	var dfs func(node int, graph [][]int, parent int, depth int) int
	dfs = func(node int, graph [][]int, parent int, depth int) int {
		if depth == 0 {
			return 1
		}
		res := 1
		for _, next := range graph[node] {
			if next != parent {
				res += dfs(next, graph, node, depth-1)
			}
		}
		return res
	}
	res := make([]int, n)
	max2 := 0
	if k > 0 {
		for i := 0; i < m; i++ {
			max2 = max(dfs(i, graph2, -1, k-1), max2)
		}
	}
	for i := 0; i < n; i++ {
		res[i] = dfs(i, graph1, -1, k) + max2
	}
	return res
}
