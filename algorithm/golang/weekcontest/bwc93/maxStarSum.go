package bwc93

import (
	"container/heap"
	"math"
)

// 超时
func maxStarSum(vals []int, edges [][]int, k int) int {
	n := len(vals)
	arr := make([]IntHeap, n)
	for _, edge := range edges {
		if vals[edge[1]] > 0 {
			heap.Init(&arr[edge[0]])
			heap.Push(&arr[edge[0]], vals[edge[1]])
			//arr[edge[0]].Push(vals[edge[1]])
			if len(arr[edge[0]]) > k {
				heap.Pop(&arr[edge[0]])
				// arr[edge[0]].Pop()
			}
		}
		if vals[edge[0]] > 0 {
			heap.Init(&arr[edge[1]])
			heap.Push(&arr[edge[1]], vals[edge[0]])
			// arr[edge[1]].Push(vals[edge[0]])
			if len(arr[edge[1]]) > k {
				//arr[edge[1]].Pop()
				heap.Pop(&arr[edge[1]])
			}
		}
	}
	res := math.MinInt32
	for i, val := range vals {
		sum := val
		for _, neighbor := range arr[i] {
			sum += neighbor
		}
		if sum > res {
			res = sum
		}
	}
	return res
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	//x := old[0]
	//*h = old[1:]
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
