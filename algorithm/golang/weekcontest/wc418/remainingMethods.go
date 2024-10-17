package wc418

// 先通过bfs把所有的可疑方法标记出来
// 并且把可疑方法的调用的点的入度-1，如果一个可疑方法调用的入度为0，那么即可删除
func remainingMethods(n int, k int, invocations [][]int) []int {
	degree := make([]int, n)
	graph := make([][]int, n)
	for _, invo := range invocations {
		x, y := invo[0], invo[1]
		graph[x] = append(graph[x], y)
		degree[y]++
	}
	visit := make([]bool, n)
	visit[k] = true
	queue := []int{k}
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			for _, next := range graph[queue[i]] {
				degree[next]--
				if !visit[next] {
					queue = append(queue, next)
					visit[next] = true
				}
			}
		}
		queue = queue[size:]
	}
	res := make([]int, 0, n)
	flag := true
	for i := 0; i < n; i++ {
		if visit[i] {
			if degree[i] > 0 {
				flag = false
				break
			}
		}
	}
	for i := 0; i < n; i++ {
		if !flag || !visit[i] {
			res = append(res, i)
		}
	}
	return res
}
