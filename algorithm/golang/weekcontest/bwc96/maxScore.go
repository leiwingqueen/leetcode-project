package bwc96

import (
	"container/heap"
	"sort"
)

// 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。
//
//对于选择的下标 i0 ，i1 ，...， ik - 1 ，你的 分数 定义如下：
//
//nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值 。
//用公示表示： (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]) 。
//请你返回 最大 可能的分数。
//
//一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。
//
//
//
//示例 1：
//
//输入：nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
//输出：12
//解释：
//四个可能的子序列分数为：
//- 选择下标 0 ，1 和 2 ，得到分数 (1+3+3) * min(2,1,3) = 7 。
//- 选择下标 0 ，1 和 3 ，得到分数 (1+3+2) * min(2,1,4) = 6 。
//- 选择下标 0 ，2 和 3 ，得到分数 (1+3+2) * min(2,3,4) = 12 。
//- 选择下标 1 ，2 和 3 ，得到分数 (3+3+2) * min(1,3,4) = 8 。
//所以最大分数为 12 。
//示例 2：
//
//输入：nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
//输出：30
//解释：
//选择下标 2 最优：nums1[2] * nums2[2] = 3 * 10 = 30 是最大可能分数。
//
//
//提示：
//
//n == nums1.length == nums2.length
//1 <= n <= 105
//0 <= nums1[i], nums2[j] <= 105
//1 <= k <= n
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-subsequence-score
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// f(n,k)=max{f(n-1,k),f(n-1,k-1)+A[i]}
// nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
// [3,1,3,2],[1,2,3,4]
func maxScore(nums1 []int, nums2 []int, k int) int64 {
	n := len(nums1)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = []int{nums1[i], nums2[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][1] <= arr[j][1]
	})
	p := n - 1
	var sum int64
	intHeap := &IntHeap{}
	var res int64
	for p >= 0 {
		num := arr[p][0]
		if n-p <= k {
			sum += int64(num)
			heap.Push(intHeap, num)
			if n-p == k {
				res = sum * int64(num)
			}
			p--
		} else {
			if num > (*intHeap)[0] {
				pop := heap.Pop(intHeap).(int)
				heap.Push(intHeap, num)
				sum += int64(num) - int64(pop)
				sub := sum * int64(num)
				if sub > res {
					res = sub
				}
			}
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
	// n := len(old)
	x := old[0]
	*h = old[1:]
	//x := old[n-1]
	//*h = old[0 : n-1]
	return x
}
