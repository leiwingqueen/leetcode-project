package bwc155

// bfs
func baseUnitConversions(conversions [][]int) []int {
	mod := 1_000_000_007
	n := len(conversions) + 1
	graph := make([][][]int, n)
	for _, edge := range conversions {
		x, y, w := edge[0], edge[1], edge[2]
		graph[x] = append(graph[x], []int{y, w})
	}
	queue := []int{0}
	res := make([]int, n)
	res[0] = 1
	for len(queue) > 0 {
		size := len(queue)
		for _, x := range queue[:size] {
			for _, next := range graph[x] {
				y, w := next[0], next[1]
				if res[y] == 0 {
					res[y] = (res[x] * w) % mod
					queue = append(queue, y)
				}
			}
		}
		queue = queue[size:]
	}
	return res
}
