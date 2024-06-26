package bwc128

import "container/heap"

// dijkstra
func minimumTime(n int, edges [][]int, disappear []int) []int {
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
	res[0] = 0
	queue := hp{{0, 0}}
	for len(queue) > 0 {
		node := heap.Pop(&queue).(pair)
		// node := queue.Pop().(pair)
		if flag[node.x] {
			continue
		}
		flag[node.x] = true
		if disappear[node.x] <= res[node.x] {
			res[node.x] = -1
			continue
		}
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
