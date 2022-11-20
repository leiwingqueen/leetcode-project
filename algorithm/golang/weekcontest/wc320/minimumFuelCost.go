package wc320

func minimumFuelCost(roads [][]int, seats int) int64 {
	n := len(roads) + 1
	graph := make([][]int, n)
	for _, road := range roads {
		from := road[0]
		to := road[1]
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	queue := []int{0}
	visit := make([]bool, n)
	visit[0] = true
	var res int64
	revert := make([]int, n)
	var leaf [][]int
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			childCnt := 0
			for _, next := range graph[queue[i]] {
				if !visit[next] {
					childCnt++
					queue = append(queue, next)
					visit[next] = true
					revert[next] = queue[i]
				}
			}
			if childCnt == 0 {
				leaf = append(leaf, []int{queue[i], depth})
			}
		}
		queue = queue[size:]
		depth++
	}
	nodeCnt := make([]int, n)
	for _, node := range leaf {
		curNode := node[0]
		curDepth := 0
		for curNode > 0 {
			if nodeCnt[curNode] == 0 {
				nodeCnt[curNode]++
			}
			nodeCnt[curNode] += curDepth
			curNode = revert[curNode]
			curDepth++
		}
	}
	// 这里还是有问题
	for i := 1; i < n; i++ {
		if nodeCnt[i]%seats == 0 {
			res += int64(nodeCnt[i] / seats)
		} else {
			res += int64(nodeCnt[i]/seats) + 1
		}
	}
	return res
}
