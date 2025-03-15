package wc440

import (
	"container/heap"
	"sort"
)

// 给你两个整数数组，nums1 和 nums2，长度均为 n，以及一个正整数 k 。
//
//对从 0 到 n - 1 每个下标 i ，执行下述操作：
//
//找出所有满足 nums1[j] 小于 nums1[i] 的下标 j 。
//从这些下标对应的 nums2[j] 中选出 至多 k 个，并 最大化 这些值的总和作为结果。
//返回一个长度为 n 的数组 answer ，其中 answer[i] 表示对应下标 i 的结果。
//
//
//
//示例 1：
//
//输入：nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2
//
//输出：[80,30,0,80,50]
//
//解释：
//
//对于 i = 0 ：满足 nums1[j] < nums1[0] 的下标为 [1, 2, 4] ，选出其中值最大的两个，结果为 50 + 30 = 80 。
//对于 i = 1 ：满足 nums1[j] < nums1[1] 的下标为 [2] ，只能选择这个值，结果为 30 。
//对于 i = 2 ：不存在满足 nums1[j] < nums1[2] 的下标，结果为 0 。
//对于 i = 3 ：满足 nums1[j] < nums1[3] 的下标为 [0, 1, 2, 4] ，选出其中值最大的两个，结果为 50 + 30 = 80 。
//对于 i = 4 ：满足 nums1[j] < nums1[4] 的下标为 [1, 2] ，选出其中值最大的两个，结果为 30 + 20 = 50 。
//示例 2：
//
//输入：nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1
//
//输出：[0,0,0,0]
//
//解释：由于 nums1 中的所有元素相等，不存在满足条件 nums1[j] < nums1[i]，所有位置的结果都是 0 。
//
//
//
//提示：
//
//n == nums1.length == nums2.length
//1 <= n <= 105
//1 <= nums1[i], nums2[i] <= 106
//1 <= k <= n

func findMaxSum(nums1 []int, nums2 []int, k int) []int64 {
	n := len(nums1)
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		arr[i] = i
	}
	sort.Slice(arr, func(i, j int) bool {
		return nums1[arr[i]] < nums1[arr[j]]
	})
	h := &MinHeap{}
	heap.Init(h) // 建堆
	res := make([]int64, n)
	res[arr[0]] = 0
	heap.Push(h, nums2[arr[0]])
	sum := int64(nums2[arr[0]])
	for i := 1; i < n; i++ {
		cur := arr[i]
		pre := arr[i-1]
		if nums1[cur] == nums1[pre] {
			res[cur] = res[pre]
		} else {
			res[cur] = sum
		}
		// 更新堆
		heap.Push(h, nums2[cur])
		sum += int64(nums2[cur])
		if h.Len() > k {
			num := heap.Pop(h).(int)
			sum -= int64(num)
		}
	}
	return res
}

// MinHeap 实现了 heap.Interface 接口，是一个小顶堆
type MinHeap []int

// Len 返回堆中元素数量
func (h MinHeap) Len() int {
	return len(h)
}

// Less 定义堆中元素的比较方式
// 小顶堆要求堆顶为最小值，因此使用 "<"
func (h MinHeap) Less(i, j int) bool {
	return h[i] < h[j]
}

// Swap 交换两个元素的位置
func (h MinHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

// Push 向堆中添加一个元素
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

// Pop 从堆中弹出一个元素
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}
