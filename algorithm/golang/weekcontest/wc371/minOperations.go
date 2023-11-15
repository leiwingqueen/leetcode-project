package wc371

// 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，这两个数组的长度都是 n 。
//
//你可以执行一系列 操作（可能不执行）。
//
//在每次操作中，你可以选择一个在范围 [0, n - 1] 内的下标 i ，并交换 nums1[i] 和 nums2[i] 的值。
//
//你的任务是找到满足以下条件所需的 最小 操作次数：
//
//nums1[n - 1] 等于 nums1 中所有元素的 最大值 ，即 nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1]) 。
//nums2[n - 1] 等于 nums2 中所有元素的 最大值 ，即 nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1]) 。
//以整数形式，表示并返回满足上述 全部 条件所需的 最小 操作次数，如果无法同时满足两个条件，则返回 -1 。
//
//
//
//示例 1：
//
//输入：nums1 = [1,2,7]，nums2 = [4,5,3]
//输出：1
//解释：在这个示例中，可以选择下标 i = 2 执行一次操作。
//交换 nums1[2] 和 nums2[2] 的值，nums1 变为 [1,2,3] ，nums2 变为 [4,5,7] 。
//同时满足两个条件。
//可以证明，需要执行的最小操作次数为 1 。
//因此，答案是 1 。
//示例 2：
//
//输入：nums1 = [2,3,4,5,9]，nums2 = [8,8,4,4,4]
//输出：2
//解释：在这个示例中，可以执行以下操作：
//首先，选择下标 i = 4 执行操作。
//交换 nums1[4] 和 nums2[4] 的值，nums1 变为 [2,3,4,5,4] ，nums2 变为 [8,8,4,4,9] 。
//然后，选择下标 i = 3 执行操作。
//交换 nums1[3] 和 nums2[3] 的值，nums1 变为 [2,3,4,4,4] ，nums2 变为 [8,8,4,5,9] 。
//同时满足两个条件。
//可以证明，需要执行的最小操作次数为 2 。
//因此，答案是 2 。
//示例 3：
//
//输入：nums1 = [1,5,4]，nums2 = [2,5,3]
//输出：-1
//解释：在这个示例中，无法同时满足两个条件。
//因此，答案是 -1 。
//
//
//提示：
//
//1 <= n == nums1.length == nums2.length <= 1000
//1 <= nums1[i] <= 109
//1 <= nums2[i] <= 109

// 贪心
func minOperations(nums1 []int, nums2 []int) int {
	n := len(nums1)
	check := func() int {
		cnt := 0
		for i := 0; i < n-1; i++ {
			if nums1[i] <= nums1[n-1] && nums2[i] <= nums2[n-1] {
				continue
			} else if nums2[i] <= nums1[n-1] && nums1[i] <= nums2[n-1] {
				cnt++
			} else {
				return -1
			}
		}
		return cnt
	}
	c1 := check()
	nums1[n-1], nums2[n-1] = nums2[n-1], nums1[n-1]
	c2 := check()
	if c2 >= 0 {
		c2++
	}
	if c1 < 0 && c2 < 0 {
		return -1
	} else if c1 < 0 {
		return c2
	} else if c2 < 0 {
		return c1
	} else {
		if c1 < c2 {
			return c1
		} else {
			return c2
		}
	}
}
