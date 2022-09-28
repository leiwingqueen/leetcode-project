package math

import (
	"container/heap"
	"sort"
)

// 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
//
//示例 1:
//
//输入: k = 5
//
//输出: 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func getKthMagicNumber(k int) int {
	nums := []int{3, 5, 7}
	h := &hp{sort.IntSlice{1}}
	visit := map[int]struct{}{1: {}}
	for i := 1; ; i++ {
		x := heap.Pop(h).(int)
		if i == k {
			return x
		}
		for _, num := range nums {
			next := num * x
			if _, has := visit[next]; !has {
				heap.Push(h, next)
				visit[next] = struct{}{}
			}
		}
	}
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
