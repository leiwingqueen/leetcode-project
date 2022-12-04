package wc322

func minScore(n int, roads [][]int) int {
	queue := []int{0}
	visit := make([]bool, n)
	visit[0] = true
	graph := make([][][]int, n)
	for _, road := range roads {
		from := road[0] - 1
		to := road[1] - 1
		graph[from] = append(graph[from], []int{to, road[2]})
		graph[to] = append(graph[to], []int{from, road[2]})
	}
	end := false
	res := -1
	for len(queue) > 0 {
		pop := queue[0]
		queue = queue[1:]
		for _, next := range graph[pop] {
			if next[0] == n-1 {
				end = true
			}
			if !visit[next[0]] {
				if res < 0 || next[1] < res {
					res = next[1]
				}
				queue = append(queue, next[0])
				visit[next[0]] = true
			}
		}
	}
	if !end {
		return -1
	} else {
		return res
	}
}
