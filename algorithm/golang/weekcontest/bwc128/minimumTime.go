package bwc128

// dijkstra
func minimumTime(n int, edges [][]int, disappear []int) []int {
	graph := make([]map[int]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make(map[int]int)
	}
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		if v, ok := graph[x][y]; !ok || v > w {
			graph[x][y] = w
			graph[y][x] = w
		}
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		res[i] = -1
	}
	res[0] = 0
	queue := make(map[int]struct{})
	for i := 0; i < n; i++ {
		queue[i] = struct{}{}
	}
	findMin := func() int {
		choose := -1
		for node := range queue {
			if res[node] >= 0 && (choose < 0 || res[choose] < 0 || res[choose] < res[node]) {
				choose = node
			}
		}
		return choose
	}
	for len(queue) > 0 {
		node := findMin()
		if node < 0 {
			break
		}
		delete(queue, node)
		if disappear[node] <= res[node] {
			res[node] = -1
			continue
		}
		for k, w := range graph[node] {
			if res[k] < 0 || res[node]+w < res[k] {
				res[k] = res[node] + w
			}
		}
	}
	return res
}
