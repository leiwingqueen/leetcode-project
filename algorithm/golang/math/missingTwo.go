package math

import "sort"

// 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
//
//以任意顺序返回这两个数字均可。
//
//示例 1:
//
//输入: [1]
//输出: [2,3]
//示例 2:
//
//输入: [2,3]
//输出: [1,4]
//提示：
//
//nums.length <= 30000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/missing-two-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func missingTwo(nums []int) []int {
	sort.Ints(nums)
	n := len(nums) + 2
	sum := 0
	for _, num := range nums {
		sum += num
	}
	diff := (n+1)*n/2 - sum
	c := diff / 2
	// 找到第一个>c的下标
	var search func(c int) int
	search = func(c int) int {
		l := 0
		r := len(nums) - 1
		for l < r {
			mid := l + (r-l)/2
			if nums[mid] > c {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	split := search(c)
	//[0,split),[split,n-2)
	s1 := 0
	for i := 0; i < split; i++ {
		s1 += nums[i]
	}
	nums1 := (split+1)*(split)/2 - s1
	nums2 := diff - nums1
	return []int{nums1, nums2}
}
