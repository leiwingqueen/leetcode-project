package binarysearch

import "sort"

// 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
//
//每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
//
//请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
//
//
//
//示例 1：
//
//输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
//输出：3
//解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
//- 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
//- 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
//- 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
//示例 2：
//
//输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
//输出：-1
//解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
//示例 3：
//
//输入：nums1 = [6,6], nums2 = [1]
//输出：3
//解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
//- 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
//- 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
//- 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
//
//
//提示：
//
//1 <= nums1.length, nums2.length <= 105
//1 <= nums1[i], nums2[i] <= 6
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func minOperations(nums1 []int, nums2 []int) int {
	n1 := len(nums1)
	n2 := len(nums2)
	s1 := 0
	for _, num := range nums1 {
		s1 += num
	}
	s2 := 0
	for _, num := range nums2 {
		s2 += num
	}
	if s1 == s2 {
		return 0
	}
	if n1 > n2*6 || n1*6 < n2 {
		return -1
	}
	if s1 > s2 {
		// swap
		s1, s2 = s2, s1
		nums1, nums2 = nums2, nums1
		n1, n2 = n2, n1
	}
	sort.Ints(nums1)
	sort.Ints(nums2)
	p1 := 0
	p2 := n2 - 1
	cnt := 0
	diff := s2 - s1
	for diff > 0 {
		if p1 == n1 {
			diff -= nums2[p2] - 1
			p2--
		} else if p2 < 0 {
			diff -= 6 - nums1[p1]
			p1++
		} else {
			if 6-nums1[p1] >= nums2[p2]-1 {
				diff -= 6 - nums1[p1]
				p1++
			} else {
				diff -= nums2[p2] - 1
				p2--
			}
		}
		cnt++
	}
	return cnt
}
