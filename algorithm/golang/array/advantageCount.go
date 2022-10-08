package array

import "sort"

// 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
//
//返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
//
//
//
//示例 1：
//
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
//示例 2：
//
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
//
//
//提示：
//
//1 <= nums1.length <= 105
//nums2.length == nums1.length
//0 <= nums1[i], nums2[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/advantage-shuffle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func advantageCount(nums1 []int, nums2 []int) []int {
	n := len(nums1)
	sort.Ints(nums1)
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		arr[i] = i
	}
	sort.Slice(arr, func(i, j int) bool {
		return nums2[arr[i]] < nums2[arr[j]]
	})
	p1 := 0
	p2 := 0
	res := make([]int, n)
	skip := make([]int, 0)
	for p1 < n {
		for p1 < n && nums1[p1] <= nums2[arr[p2]] {
			skip = append(skip, nums1[p1])
			p1++
		}
		if p1 == n {
			break
		}
		res[arr[p2]] = nums1[p1]
		p1++
		p2++
	}
	for p2 < n {
		res[arr[p2]] = skip[0]
		p2++
		skip = skip[1:]
	}
	return res
}
