package math

import "math"

// 给你一个数组 nums 和一个整数 k 。你需要找到 nums 的一个
//子数组
// ，满足子数组中所有元素按位或运算 OR 的值与 k 的 绝对差 尽可能 小 。换言之，你需要选择一个子数组 nums[l..r] 满足 |k - (nums[l] OR nums[l + 1] ... OR nums[r])| 最小。
//
//请你返回 最小 的绝对差值。
//
//子数组 是数组中连续的 非空 元素序列。
//
//
//
//示例 1：
//
//输入：nums = [1,2,4,5], k = 3
//
//输出：0
//
//解释：
//
//子数组 nums[0..1] 的按位 OR 运算值为 3 ，得到最小差值 |3 - 3| = 0 。
//
//示例 2：
//
//输入：nums = [1,3,1,3], k = 2
//
//输出：1
//
//解释：
//
//子数组 nums[1..1] 的按位 OR 运算值为 3 ，得到最小差值 |3 - 2| = 1 。
//
//示例 3：
//
//输入：nums = [1], k = 10
//
//输出：9
//
//解释：
//
//只有一个子数组，按位 OR 运算值为 1 ，得到最小差值 |10 - 1| = 9 。
//
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= k <= 109

// 暴力
func minimumDifference(nums []int, k int) int {
	abs := func(num int) int {
		if num >= 0 {
			return num
		} else {
			return -num
		}
	}
	n := len(nums)
	res := math.MaxInt
	for i := 0; i < n; i++ {
		// i是右边界
		or := 0
		for j := i; j >= 0; j-- {
			or |= nums[j]
			res = min(res, abs(k-or))
		}
	}
	return res
}

func minimumDifference2(nums []int, k int) int {
	abs := func(num int) int {
		if num >= 0 {
			return num
		} else {
			return -num
		}
	}
	n := len(nums)
	mp := make(map[int]struct{})
	res := math.MaxInt
	for i := 0; i < n; i++ {
		tmp := make(map[int]struct{})
		for v := range mp {
			v |= nums[i]
			res = min(res, abs(k-v))
			tmp[v] = struct{}{}
		}
		res = min(res, abs(k-nums[i]))
		tmp[nums[i]] = struct{}{}
		mp = tmp
	}
	return res
}
