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
	for i := 0; i < n-2; i++ {
		x := nums[i]
		suffix[x]--
		// 假设这个数字只出现一次（两边都没有出现）
		if i >= 2 {
			cnt += comb(i-1-prefix[x]) * comb(n-i-1-suffix[x])
		}
		// 假设这个数字出现2次，这里的场景会比较复杂
		// 1. 左边出现1次，右边没有出现。存在一个数字y，y左边出现1次，右边出现一次
		// 2. 左边出现1次，右边没有出现。存在一个数字y，右边出现两次
		if prefix[x] > 0 {
			for y := range suffix {
				if y != x && suffix[y] > 0 {
					cnt += prefix[x] * prefix[y] * (n - i - 1 - suffix[x]) * suffix[y]
					if suffix[y] >= 2 {
						cnt += prefix[x] * (i - prefix[x]) * comb(suffix[y])
					}
				}
			}
		}
		// 3. 左边出现0次，右边出现一次。存在一个数字y，y左边出现一次，右边出现一次
		// 4. 左边出现0次，右边出现一次。存在一个数字y，y左边出现两次
		if suffix[x] > 0 {
			for y := range prefix {
				if y != x && prefix[y] > 0 {
					cnt += suffix[x] * suffix[y] * (i - 1 - prefix[x]) * prefix[y]
					if prefix[y] >= 2 {
						cnt += suffix[x] * (n - i - 1 - suffix[x]) * comb(prefix[y])
					}
				}
			}
		}
		prefix[x]++
	}
	return comb2(n, 5) - cnt
}
