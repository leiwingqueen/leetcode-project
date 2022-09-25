package wc312

import "sort"

// 超时
func numberOfGoodPaths(vals []int, edges [][]int) int {
	n := len(vals)
	graph := make([][]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make([]int, 0)
	}
	for _, edge := range edges {
		graph[edge[0]] = append(graph[edge[0]], edge[1])
		graph[edge[1]] = append(graph[edge[1]], edge[0])
	}
	var dfs func(start int, cur int, visit []bool) int
	dfs = func(start int, cur int, visit []bool) int {
		r := 0
		if vals[cur] == vals[start] && start != cur {
			r++
		}
		for _, next := range graph[cur] {
			if !visit[next] && vals[next] <= vals[start] {
				visit[next] = true
				r += dfs(start, next, visit)
				visit[next] = false
			}
		}
		return r
	}
	res := 0
	for i := 0; i < n; i++ {
		visit := make([]bool, n)
		visit[i] = true
		res += dfs(i, i, visit)
	}
	return res/2 + n
}

// 并查集
func numberOfGoodPaths2(vals []int, edges [][]int) int {
	n := len(vals)
	graph := make([][]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make([]int, 0)
	}
	// 根据值进行排序
	ids := make([]int, n)
	for i := 0; i < n; i++ {
		ids[i] = i
	}
	sort.Slice(ids, func(i, j int) bool {
		return vals[ids[i]] < vals[ids[j]]
	})
	// 并查集
	parent := make([]int, n)
	cnt := make([]int, n)
	for i := 0; i < n; i++ {
		parent[i] = i
		cnt[i] = 1
	}
	var find func(x int) int
	find = func(x int) int {
		for parent[x] != x {
			parent[x] = parent[parent[x]]
		}
		return parent[x]
	}
	res := 0
	for _, id := range ids {
		vx := vals[id]
		p := find(id)
		// 判断是否要加入该边
		for _, next := range graph[id] {
			p2 := find(next)
			if vals[p2] > vx || parent[p2] == p {
				continue
			}
			if vals[p2] == vx {
				res += cnt[p2] * cnt[p]
				cnt[p] += cnt[p2]
			}
			parent[p2] = id
		}
	}
	return res
}
