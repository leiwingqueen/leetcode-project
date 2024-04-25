package wc394

import (
	"container/heap"
)

// 给你一个 n 个节点的无向带权图，节点编号为 0 到 n - 1 。图中总共有 m 条边，用二维数组 edges 表示，其中 edges[i] = [ai, bi, wi] 表示节点 ai 和 bi 之间有一条边权为 wi 的边。
//
//对于节点 0 为出发点，节点 n - 1 为结束点的所有最短路，你需要返回一个长度为 m 的 boolean 数组 answer ，如果 edges[i] 至少 在其中一条最短路上，那么 answer[i] 为 true ，否则 answer[i] 为 false 。
//
//请你返回数组 answer 。
//
//注意，图可能不连通。
//
//
//
//示例 1：
//
//
//
//输入：n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]
//
//输出：[true,true,true,false,true,true,true,false]
//
//解释：
//
//以下为节点 0 出发到达节点 5 的 所有 最短路：
//
//路径 0 -> 1 -> 5 ：边权和为 4 + 1 = 5 。
//路径 0 -> 2 -> 3 -> 5 ：边权和为 1 + 1 + 3 = 5 。
//路径 0 -> 2 -> 3 -> 1 -> 5 ：边权和为 1 + 1 + 2 + 1 = 5 。
//示例 2：
//
//
//
//输入：n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]
//
//输出：[true,false,false,true]
//
//解释：
//
//只有一条从节点 0 出发到达节点 3 的最短路 0 -> 2 -> 3 ，边权和为 1 + 2 = 3 。

// 接近答案了，但还是有个问题
func findAnswer(n int, edges [][]int) []bool {
	dis := dijkstra(n, edges, 0)
	buildKey := func(x, y int) int64 {
		if x > y {
			x, y = y, x
		}
		return int64(x)<<32 | int64(y)
	}
	graph := make([]map[int]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make(map[int]int)
	}
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		if v, ok := graph[x][y]; !ok || v > w {
			graph[x][y] = w
			graph[y][x] = w
		}
	}
	edgeMap := make(map[int64]int)
	for i, edge := range edges {
		x, y := edge[0], edge[1]
		edgeMap[buildKey(x, y)] = i
	}
	res := make([]bool, len(edges))
	if dis[n-1] < 0 {
		return res
	}
	// bfs反向求路径
	var queue []int
	queue = append(queue, n-1)
	visit := make([]bool, n)
	visit[n-1] = true
	for len(queue) > 0 {
		size := len(queue)
		for _, node := range queue {
			for next, w := range graph[node] {
				if dis[next] >= 0 && dis[node] == dis[next]+w {
					// 在最短路径上
					idx := edgeMap[buildKey(node, next)]
					res[idx] = true
					if !visit[next] {
						queue = append(queue, next)
						visit[next] = true
					}
				}
			}
		}
		queue = queue[size:]
	}
	return res
}

func dijkstra(n int, edges [][]int, start int) []int {
	graph := make([]map[int]int, n)
	for i := 0; i < n; i++ {
		graph[i] = make(map[int]int)
	}
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		if v, ok := graph[x][y]; !ok || v > w {
			graph[x][y] = w
			graph[y][x] = w
		}
	}
	res := make([]int, n)
	flag := make([]bool, n)
	for i := 0; i < n; i++ {
		res[i] = -1
	}
	res[start] = 0
	queue := hp{{0, start}}
	for len(queue) > 0 {
		node := heap.Pop(&queue).(pair)
		// node := queue.Pop().(pair)
		if flag[node.x] {
			continue
		}
		flag[node.x] = true
		for k, w := range graph[node.x] {
			if flag[k] {
				continue
			}
			if res[k] < 0 || res[node.x]+w < res[k] {
				res[k] = res[node.x] + w
				heap.Push(&queue, pair{res[k], k})
			}
		}
	}
	return res
}

type pair struct{ dis, x int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() (v any)      { a := *h; *h, v = a[:len(a)-1], a[len(a)-1]; return }
