package wc338

func collectTheCoins(coins []int, edges [][]int) int {
	n := len(coins)
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	return 0
}
