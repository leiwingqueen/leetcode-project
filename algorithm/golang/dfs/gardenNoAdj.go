package dfs

func gardenNoAdj(n int, paths [][]int) []int {
	graph := make([][]int, n)
	for _, path := range paths {
		from, to := path[0]-1, path[1]-1
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	res := make([]int, n)
	var dfs func(idx int)
	dfs = func(idx int) {
		if res[idx] > 0 {
			return
		}
		used := make([]bool, 4)
		for _, to := range graph[idx] {
			if res[to] > 0 {
				used[res[to]-1] = true
			}
		}
		for i := 0; i < 4; i++ {
			if !used[i] {
				res[idx] = i + 1
				break
			}
		}
		for _, to := range graph[idx] {
			if res[to] == 0 {
				dfs(to)
			}
		}
	}
	for i := 0; i < n; i++ {
		dfs(i)
	}
	return res
}
