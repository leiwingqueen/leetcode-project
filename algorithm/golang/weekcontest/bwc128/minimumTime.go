package bwc128

import (
	"container/heap"
	"fmt"
)

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
	for i := 0; i < n; i++ {
		res[i] = -1
	}
	res[0] = 0
	queue := make(map[int]struct{})
	for i := 0; i < n; i++ {
		queue[i] = struct{}{}
	}
	findMin := func() int {
		choose := -1
		for node := range queue {
			if res[node] >= 0 && (choose < 0 || res[choose] < 0 || res[choose] > res[node]) {
				choose = node
			}
		}
		return choose
	}
	for len(queue) > 0 {
		node := findMin()
		if node < 0 {
			break
		}
		delete(queue, node)
		if disappear[node] <= res[node] {
			res[node] = -1
			continue
		}
		for k, w := range graph[node] {
			if _, ok := queue[k]; !ok {
				continue
			}
			if res[k] < 0 || res[node]+w < res[k] {
				res[k] = res[node] + w
			}
		}
	}
	return res
}

// IndexHeap 是一个由整数索引构成的最小堆。
// 这些索引指向一个外部的 `res` 数组。
type IndexHeap struct {
	indices []int // 存储索引的切片
	res     []int // 引用的外部数组
}

// Len 是heap.Interface要求的方法，返回堆中的元素数量。
func (h IndexHeap) Len() int { return len(h.indices) }

// Less 是heap.Interface要求的方法，决定堆的顺序。
// 使用 res 数组的值来比较两个索引。
func (h IndexHeap) Less(i, j int) bool { return h.res[h.indices[i]] < h.res[h.indices[j]] }

// Swap 是heap.Interface要求的方法，交换元素i和j的位置。
func (h IndexHeap) Swap(i, j int) { h.indices[i], h.indices[j] = h.indices[j], h.indices[i] }

// Push 是heap.Interface要求的方法，用来向堆中插入一个新索引。
func (h *IndexHeap) Push(x interface{}) {
	h.indices = append(h.indices, x.(int))
}

// Pop 是heap.Interface要求的方法，用来从堆中取出最顶端的索引。
func (h *IndexHeap) Pop() interface{} {
	old := h.indices
	n := len(old)
	x := old[n-1]
	h.indices = old[0 : n-1]
	return x
}

func main() {
	res := []int{15, 3, 20, 7, 9, 30}
	h := &IndexHeap{res: res}
	heap.Init(h)
	for i := range res {
		heap.Push(h, i)
	}
	for h.Len() > 0 {
		fmt.Printf("%d ", heap.Pop(h).(int)) // 输出的是索引，而不是值
	}
}
