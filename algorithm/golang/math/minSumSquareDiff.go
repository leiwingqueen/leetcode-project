package math

import "sort"

// 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。
//
//数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])2 之和。
//
//同时给你两个正整数 k1 和 k2 。你可以将 nums1 中的任意元素 +1 或者 -1 至多 k1 次。类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。
//
//请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。
//
//注意：你可以将数组中的元素变成 负 整数。
//
//
//
//示例 1：
//
//输入：nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
//输出：579
//解释：nums1 和 nums2 中的元素不能修改，因为 k1 = 0 和 k2 = 0 。
//差值平方和为：(1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579 。
//示例 2：
//
//输入：nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
//输出：43
//解释：一种得到最小差值平方和的方式为：
//- 将 nums1[0] 增加一次。
//- 将 nums2[2] 增加一次。
//最小差值平方和为：
//(2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43 。
//注意，也有其他方式可以得到最小差值平方和，但没有得到比 43 更小答案的方案。
//
//
//提示：
//
//n == nums1.length == nums2.length
//1 <= n <= 105
//0 <= nums1[i], nums2[i] <= 105
//0 <= k1, k2 <= 109

func minSumSquareDiff(nums1 []int, nums2 []int, k1 int, k2 int) int64 {
	square := func(num int) int64 {
		return int64(num) * int64(num)
	}
	abs := func(num int) int {
		if num < 0 {
			return -num
		}
		return num
	}
	calDiff := func(num int, d int) int64 {
		return 2*int64(num-d)*int64(d) + int64(d)*int64(d)
	}
	k := k1 + k2
	n := len(nums1)
	// 最后一个diff是0，这是为了方便处理最后一个diff的临界情况
	diff := make([]int, n+1)
	var sum int64
	for i := 0; i < n; i++ {
		diff[i] = abs(nums1[i] - nums2[i])
		sum += square(diff[i])
	}
	sort.Slice(diff, func(i, j int) bool {
		return diff[i] > diff[j]
	})
	for i := 0; i < n; i++ {
		if k == 0 {
			break
		}
		d := diff[i] - diff[i+1]
		if d == 0 {
			continue
		}
		// 假设前面都能同时减到diff[i]
		cnt := i + 1
		if k >= d*cnt {
			k -= d * cnt
			sum -= calDiff(diff[i], d) * int64(cnt)
		} else {
			// 先计算前面i个元素能减去多少
			q := k / cnt
			if q > 0 {
				k -= q * cnt
				sum -= calDiff(diff[i], q) * int64(cnt)
			}
			p := k - q*cnt
			if p > 0 {
				k -= p
				sum -= calDiff(diff[i], 1) * int64(p)
			}
		}
	}
	return sum
}
