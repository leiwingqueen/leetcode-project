package wc351

// 给你一个二元数组 nums 。
//
//如果数组中的某个子数组 恰好 只存在 一 个值为 1 的元素，则认为该子数组是一个 好子数组 。
//
//请你统计将数组 nums 划分成若干 好子数组 的方法数，并以整数形式返回。由于数字可能很大，返回其对 109 + 7 取余 之后的结果。
//
//子数组是数组中的一个连续 非空 元素序列。
//
//
//
//示例 1：
//
//输入：nums = [0,1,0,0,1]
//输出：3
//解释：存在 3 种可以将 nums 划分成若干好子数组的方式：
//- [0,1] [0,0,1]
//- [0,1,0] [0,1]
//- [0,1,0,0] [1]
//示例 2：
//
//输入：nums = [0,1,0]
//输出：1
//解释：存在 1 种可以将 nums 划分成若干好子数组的方式：
//- [0,1,0]
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 1

// 双指针
func numberOfGoodSubarraySplits(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	var arr []int
	p := 0
	for p < n {
		for p < n && nums[p] == 0 {
			p++
		}
		if p == n {
			break
		}
		arr = append(arr, p)
		p++
	}
	if len(arr) <= 0 {
		return 0
	} else if len(arr) == 1 {
		return 1
	} else {
		res := 1
		for i := 1; i < len(arr); i++ {
			res = (res * (arr[i] - arr[i-1])) % mod
		}
		return res
	}
}
