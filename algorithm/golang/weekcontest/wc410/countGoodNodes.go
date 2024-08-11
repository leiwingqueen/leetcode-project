package wc410

func countGoodNodes(edges [][]int) int {
	n := len(edges) + 1
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	res := 0
	var dfs func(node, parent int) int
	dfs = func(node, parent int) int {
		cnt := -1
		equal := true
		total := 1
		for _, next := range graph[node] {
			if next == parent {
				continue
			}
			c := dfs(next, node)
			total += c
			if cnt < 0 {
				cnt = c
			} else {
				if cnt != c {
					equal = false
				}
			}
		}
		if equal {
			res++
		}
		return total
	}
	dfs(0, -1)
	return res
}
