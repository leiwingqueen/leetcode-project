package wc381

// 暴力
func countOfPairs(n int, x int, y int) []int {
	res := make([]int, n)
	cal := func(i, j int) int {
		var queue []int
		visit := make([]bool, n)
		visit[i-1] = true
		queue = append(queue, i)
		depth := 0
		for len(queue) > 0 {
			size := len(queue)
			for _, node := range queue[0:size] {
				if node == j {
					return depth
				}
				// 左右两个方向
				if node > 1 && !visit[node-1-1] {
					queue = append(queue, node-1)
					visit[node-1-1] = true
				}
				if node < n && !visit[node+1-1] {
					queue = append(queue, node+1)
					visit[node+1-1] = true
				}
				if node == x && !visit[y-1] {
					queue = append(queue, y)
					visit[y-1] = true
				}
				if node == y && !visit[x-1] {
					queue = append(queue, x)
					visit[x-1] = true
				}
			}
			depth++
			queue = queue[size:]
		}
		return -1
	}
	for i := 1; i <= n; i++ {
		for j := i + 1; j <= n; j++ {
			d := cal(i, j)
			res[d-1] += 2
		}
	}
	return res
}
