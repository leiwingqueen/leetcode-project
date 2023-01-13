package wc327

import "container/heap"

func maxKelements(nums []int, k int) int64 {
	h := &IntHeap{[]int{}, false}
	for _, num := range nums {
		heap.Push(h, num)
	}
	var res int64
	for k > 0 {
		pop := heap.Pop(h).(int)
		res += int64(pop)
		heap.Push(h, (pop+2)/3)
		k--
	}
	return res
}

type IntHeap struct {
	heap []int
	//true是小根堆，false是大根堆
	bool
}

func (h IntHeap) Len() int { return len(h.heap) }
func (h IntHeap) Less(i, j int) bool {
	if h.bool {
		return h.heap[i] < h.heap[j]
	} else {
		return h.heap[i] > h.heap[j]
	}
}                               // 小根堆  > 大根堆
func (h IntHeap) Swap(i, j int) { h.heap[i], h.heap[j] = h.heap[j], h.heap[i] }

func (h *IntHeap) Push(x interface{}) {
	h.heap = append(h.heap, x.(int))
}

func (h *IntHeap) Pop() interface{} {
	old := h
	n := len(old.heap)
	x := old.heap[n-1]
	h.heap = old.heap[0 : n-1]
	return x
}
