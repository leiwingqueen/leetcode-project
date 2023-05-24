package wc346

import "math"

//给你一个 n 个节点的 无向带权连通 图，节点编号为 0 到 n - 1 ，再给你一个整数数组 edges ，其中 edges[i] = [ai, bi, wi] 表示节点 ai 和 bi 之间有一条边权为 wi 的边。
//
//部分边的边权为 -1（wi = -1），其他边的边权都为 正 数（wi > 0）。
//
//你需要将所有边权为 -1 的边都修改为范围 [1, 2 * 109] 中的 正整数 ，使得从节点 source 到节点 destination 的 最短距离 为整数 target 。如果有 多种 修改方案可以使 source 和 destination 之间的最短距离等于 target ，你可以返回任意一种方案。
//
//如果存在使 source 到 destination 最短距离为 target 的方案，请你按任意顺序返回包含所有边的数组（包括未修改边权的边）。如果不存在这样的方案，请你返回一个 空数组 。
//
//注意：你不能修改一开始边权为正数的边。

func modifiedGraphEdges(n int, edges [][]int, source int, destination int, target int) [][]int {
	graph := make([][][]int, n)
	toAdd := make([][]int, 0)
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		if w >= 0 {
			graph[x] = append(graph[x], []int{y, w})
		} else {
			toAdd = append(toAdd, []int{x, y})
		}
	}
	// bfs计算每个节点离终点和起始点的最近距离
	cal := func(start int, dis []int) {
		visit := make([]bool, n)
		visit[start] = true
		for i := 0; i < n; i++ {
			dis[i] = -1
		}
		dis[start] = 0
		queue := []int{start}
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				for _, next := range graph[queue[i]] {
					node := next[0]
					if !visit[node] {
						queue = append(queue, node)
						visit[node] = true
					}
				}
			}
			queue = queue[size:]
		}
	}
	cal(source, make([]int, n))
	return [][]int{}
}

// Dijkstra
func dijkstra(n int, graph [][][]int, start int) []int {
	dis := make([]int, n)
	for i := 0; i < n; i++ {
		dis[i] = math.MaxInt
	}
	dis[start] = 0
	for _, next := range graph[start] {
		k, w := next[0], next[1]
		dis[k] = w
	}
	// 未确定的集合，已确定的集合
	process := make(map[int]struct{})
	findMinNode := func() int {
		min := math.MaxInt
		choose := -1
		for node := range process {
			if dis[node] < min {
				choose = node
				min = dis[node]
			}
		}
		return choose
	}
	for i := 0; i < n; i++ {
		if i != start {
			process[i] = struct{}{}
		}
	}
	for len(process) > 0 {
		node := findMinNode()
		if node < 0 {
			return dis
		}
		delete(process, node)
		for _, next := range graph[node] {
			k, w := next[0], next[1]
			if dis[node]+w < dis[k] {
				dis[k] = dis[node] + w
			}
		}
	}
	return dis
}
