package bwc91

import "math"

func mostProfitablePath(edges [][]int, bob int, amount []int) int {
	n := len(amount)
	graph := make([][]int, n)
	for _, edge := range edges {
		graph[edge[0]] = append(graph[edge[0]], edge[1])
		graph[edge[1]] = append(graph[edge[1]], edge[0])
	}
	visit := make([]bool, n)
	var p []int
	var dfs func(path []int, idx int, cur int)
	dfs = func(path []int, idx int, cur int) {
		if cur == bob {
			path[idx] = cur
			idx++
			p = make([]int, idx)
			for i := 0; i < idx; i++ {
				p[i] = path[i]
			}
			return
		}
		path[idx] = cur
		visit[cur] = true
		for _, next := range graph[cur] {
			if !(visit[next]) {
				dfs(path, idx+1, next)
			}
		}
	}
	dfs(make([]int, n), 0, 0)
	i := len(p) / 2
	if len(p)%2 == 1 {
		mid := p[len(p)/2]
		amount[mid] /= 2
		i++
	}
	for ; i < len(p); i++ {
		amount[p[i]] = 0
	}
	visit2 := make([]bool, n)
	var dfs2 func(cur int) int
	dfs2 = func(cur int) int {
		res := amount[cur]
		visit2[cur] = true
		mx := math.MinInt32
		for _, next := range graph[cur] {
			if !(visit2[next]) {
				visit2[next] = true
				sub := dfs2(next)
				if sub > mx {
					mx = sub
				}
			}
		}
		if mx > math.MinInt32 {
			return res + mx
		} else {
			return res
		}
	}
	return dfs2(0)
}
