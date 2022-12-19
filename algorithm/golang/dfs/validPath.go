package dfs

func validPath(n int, edges [][]int, source int, destination int) bool {
	if source == destination {
		return true
	}
	graph := make([][]int, n)
	for _, edge := range edges {
		graph[edge[0]] = append(graph[edge[0]], edge[1])
		graph[edge[1]] = append(graph[edge[1]], edge[0])
	}
	visit := make([]bool, n)
	queue := []int{source}
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			for _, next := range graph[queue[i]] {
				if !visit[next] {
					if next == destination {
						return true
					}
					visit[next] = true
					queue = append(queue, next)
				}
			}
		}
		queue = queue[size:]
	}
	return false
}
