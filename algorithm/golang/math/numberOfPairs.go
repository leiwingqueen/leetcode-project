package math

// 给你两个整数数组 nums1 和 nums2，长度分别为 n 和 m。同时给你一个正整数 k。
//
//如果 nums1[i] 可以被 nums2[j] * k 整除，则称数对 (i, j) 为 优质数对（0 <= i <= n - 1, 0 <= j <= m - 1）。
//
//返回 优质数对 的总数。
//
//
//
//示例 1：
//
//输入：nums1 = [1,3,4], nums2 = [1,3,4], k = 1
//
//输出：5
//
//解释：
//
//5个优质数对分别是 (0, 0), (1, 0), (1, 1), (2, 0), 和 (2, 2)。
//
//示例 2：
//
//输入：nums1 = [1,2,4,12], nums2 = [2,4], k = 3
//
//输出：2
//
//解释：
//
//2个优质数对分别是 (3, 0) 和 (3, 1)。
//
//
//
//提示：
//
//1 <= n, m <= 105
//1 <= nums1[i], nums2[j] <= 106
//1 <= k <= 103

// 必然超时
func numberOfPairs(nums1 []int, nums2 []int, k int) int64 {
	n, m := len(nums1), len(nums2)
	var res int64
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if nums1[i]%(nums2[j]*k) == 0 {
				res++
			}
		}
	}
	return res
}

// 稍微优化一下
func numberOfPairs2(nums1 []int, nums2 []int, k int) int64 {
	n, m := len(nums1), len(nums2)
	var res int64
	for i := 0; i < n; i++ {
		if nums1[i]%k != 0 {
			continue
		}
		nums1[i] /= k
		for j := 0; j < m; j++ {
			if nums1[i]%nums2[j] == 0 {
				res++
			}
		}
	}
	return res
}

func numberOfPairs3(nums1 []int, nums2 []int, k int) int64 {
	cnt1, cnt2 := make(map[int]int), make(map[int]int)
	mx := 0
	for _, num := range nums1 {
		if num%k == 0 {
			cnt1[num/k]++
			mx = max(mx, num/k)
		}
	}
	if len(cnt1) == 0 {
		return 0
	}
	for _, num := range nums2 {
		cnt2[num]++
	}
	var res int64
	for num, c := range cnt2 {
		for i := num; i <= mx; i += num {
			res += int64(cnt1[i]) * int64(c)
		}
	}
	return res
}
