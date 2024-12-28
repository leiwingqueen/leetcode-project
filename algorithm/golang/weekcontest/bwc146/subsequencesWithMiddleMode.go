package bwc146

// 遍历每个元素，以该元素为中间元素，分别计算左右两边的子序列个数
// 左右两边的子序列个数分别为：左边的子序列个数 * 右边的子序列个数
// 一共有几个场景：
func subsequencesWithMiddleMode(nums []int) int {
	comb := func(x int) int {
		return x * (x - 1) / 2
	}
	comb2 := func(x, k int) int {
		res := 1
		for i := 0; i < k; i++ {
			res *= x
			x--
		}
		for i := 1; i <= k; i++ {
			res /= i
		}
		return res
	}
	n := len(nums)
	// 后缀统计
	suffix := make(map[int]int)
	for i := n - 1; i >= 0; i-- {
		suffix[nums[i]]++
	}
	prefix := make(map[int]int)
	// 不合法的数量
	cnt := 0
	for i := 0; i < n; i++ {
		num := nums[i]
		suffix[num]--
		// 假设这个数字只出现一次（两边都没有出现）
		cnt += comb(i-1-prefix[num]) * comb(n-i-1-suffix[num])
		// 假设这个数字出现2次，这里的场景会比较复杂
		// 1. 左边出现1次，右边没有出现。存在一个数字y，y左边出现1次，右边出现一次
		// 2. 左边出现1次，右边没有出现。存在一个数字y，右边出现两次
		if prefix[num] > 0 {
			for _, y := range suffix {
				if y != num {
					cnt += prefix[num] * prefix[y] * (n - i - 1 - suffix[num]) * suffix[y]
					if suffix[y] >= 2 {
						cnt += prefix[num] * (i - prefix[num]) * comb(suffix[y])
					}
				}
			}
		}
		// 3. 左边出现0次，右边出现一次。存在一个数字y，y左边出现一次，右边出现一次
		// 4. 左边出现0次，右边出现一次。存在一个数字y，y左边出现两次
		if suffix[num] > 0 {
			for _, y := range prefix {
				if y != num {
					cnt += suffix[num] * suffix[y] * (i - 1 - prefix[num]) * prefix[y]
					if prefix[y] >= 2 {
						cnt += suffix[num] * (n - i - 1 - suffix[num]) * comb(prefix[y])
					}
				}
			}
		}
		prefix[num]++
	}
	return comb2(n, 5) - cnt
}
