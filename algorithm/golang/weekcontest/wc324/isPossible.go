package wc324

func isPossible(n int, edges [][]int) bool {
	degree := make([]int, n)
	graph := make([]map[int]bool, n)
	for i := 0; i < n; i++ {
		graph[i] = make(map[int]bool)
	}
	for _, edge := range edges {
		degree[edge[0]-1]++
		degree[edge[1]-1]++
		graph[edge[0]-1][edge[1]-1] = true
		graph[edge[1]-1][edge[0]-1] = true
	}
	cnt := 0
	var arr []int
	for i, d := range degree {
		if d%2 == 1 {
			arr = append(arr, i)
			cnt++
		}
	}
	if cnt == 0 {
		return true
	}
	if cnt%2 == 1 || cnt > 4 {
		return false
	}
	if len(arr) == 2 {
		if !graph[arr[0]][arr[1]] {
			return true
		}
		for i := 0; i < n; i++ {
			if i != arr[0] && i != arr[1] && !graph[i][arr[0]] && !graph[i][arr[1]] {
				return true
			}
		}
		return false
	} else {
		for i := 0; i < 3; i++ {
			for j := i + 1; j < 4; j++ {
				var left []int
				for k := 0; k < 4; k++ {
					if k != i && k != j {
						left = append(left, arr[k])
					}
				}
				if !graph[arr[i]][arr[j]] && !graph[left[0]][left[1]] {
					return true
				}
			}
		}
		return false
	}
}
