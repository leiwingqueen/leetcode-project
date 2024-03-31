package bwc127

// 给你一个 非负 整数数组 nums 和一个整数 k 。
//
//如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
//
//请你返回 nums 中 最短特别非空 子数组的长度，如果特别子数组不存在，那么返回 -1 。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3], k = 2
//
//输出：1
//
//解释：
//
//子数组 [3] 的按位 OR 值为 3 ，所以我们返回 1 。
//
//示例 2：
//
//输入：nums = [2,1,8], k = 10
//
//输出：3
//
//解释：
//
//子数组 [2,1,8] 的按位 OR 值为 11 ，所以我们返回 3 。
//
//示例 3：
//
//输入：nums = [1,2], k = 0
//
//输出：1
//
//解释：
//
//子数组 [1] 的按位 OR 值为 1 ，所以我们返回 1 。
//
//
//
//提示：
//
//1 <= nums.length <= 50
//0 <= nums[i] <= 50
//0 <= k < 64

func minimumSubarrayLength(nums []int, k int) int {
	n := len(nums)
	res := -1
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			sum := 0
			for l := i; l <= j; l++ {
				sum |= nums[l]
			}
			if sum >= k {
				if res < 0 || j-i+1 < res {
					res = j - i + 1
				}
			}
		}
	}
	return res
}

func minimumSubarrayLength2(nums []int, k int) int {
	n := len(nums)
	check := func(size int) bool {
		cnt := make([]int, 32)
		l, r := 0, 0
		sum := 0
		for ; r < size; r++ {
			for i := 0; i < 32; i++ {
				if nums[r]&(1<<i) != 0 {
					cnt[i]++
				}
			}
			sum |= nums[r]
		}
		if sum >= k {
			return true
		}
		for r < n {
			if sum >= k {
				return true
			}
			for i := 0; i < 32; i++ {
				if nums[r]&(1<<i) != 0 {
					cnt[i]++
				}
			}
			sum |= nums[r]
			for i := 0; i < 32; i++ {
				if nums[l]&(1<<i) != 0 {
					cnt[i]--
					if cnt[i] == 0 {
						sum -= 1 << i
					}
				}
			}
			l++
			r++
		}
		return sum >= k
	}
	if !check(n) {
		return -1
	}
	l, r := 1, n
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
