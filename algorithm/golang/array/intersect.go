package array

import "sort"

// 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
//
//
//
//示例 1：
//
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
//示例 2:
//
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9]
//
//
//提示：
//
//1 <= nums1.length, nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 1000
//
//
//进阶：
//
//如果给定的数组已经排好序呢？你将如何优化你的算法？
//如果 nums1 的大小比 nums2 小，哪种方法更优？
//如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

func intersect(nums1 []int, nums2 []int) []int {
	mp1, mp2 := make(map[int]int), make(map[int]int)
	for _, num := range nums1 {
		mp1[num]++
	}
	for _, num := range nums2 {
		mp2[num]++
	}
	var res []int
	for k, v := range mp1 {
		if mp2[k] > 0 {
			for i := 0; i < min(v, mp2[k]); i++ {
				res = append(res, k)
			}
		}
	}
	return res
}

func intersect2(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	p1, p2 := 0, 0
	n1, n2 := len(nums1), len(nums2)
	var res []int
	for p1 < n1 && p2 < n2 {
		if nums1[p1] == nums2[p2] {
			res = append(res, nums1[p1])
			p1++
			p2++
		} else if nums1[p1] < nums2[p2] {
			p1++
		} else {
			p2++
		}
	}
	return res
}
