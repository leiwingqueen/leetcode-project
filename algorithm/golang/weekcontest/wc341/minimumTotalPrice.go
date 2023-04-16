package wc341

func minimumTotalPrice(n int, edges [][]int, price []int, trips [][]int) int {
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	mp := make(map[int]int)
	var dfs func(parent, from, to int) bool
	dfs = func(parent, from, to int) bool {
		if from == to {
			mp[from]++
			return true
		}
		for _, next := range graph[from] {
			if next != parent {
				find := dfs(from, next, to)
				if find {
					mp[from]++
					return true
				}
			}
		}
		return false
	}
	for _, trip := range trips {
		from, to := trip[0], trip[1]
		dfs(-1, from, to)
	}
	sum := 0
	for k, v := range mp {
		sum += price[k] * v
	}
	mx := 0
	var dfs2 func(choose []bool, idx, total int)
	dfs2 = func(choose []bool, idx, total int) {
		if idx == n {
			if total > 0 {
				mx = total
			}
			return
		}
		flag := true
		for _, next := range graph[idx] {
			if choose[next] {
				flag = false
				break
			}
		}
		if flag {
			choose[idx] = true
			dfs2(choose, idx+1, total+price[idx]*mp[idx]/2)
			choose[idx] = false
		}
		dfs2(choose, idx+1, total)
	}
	dfs2(make([]bool, n), 0, 0)
	return sum - mx
}
