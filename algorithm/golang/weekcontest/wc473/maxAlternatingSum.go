package wc473

import "sort"

// 给你一个整数数组 nums。你可以以任意顺序 重新排列元素 。
//
//数组 arr 的 交替得分 定义为：
//
//score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
//在对 nums 重新排列后，返回其 最大可能的交替得分。
//
//
//
//示例 1：
//
//输入： nums = [1,2,3]
//
//输出： 12
//
//解释：
//
//nums 的一种可行重排为 [2,1,3]，该排列在所有可能重排中给出了最大交替得分。
//
//交替得分计算如下：
//
//score = 22 - 12 + 32 = 4 - 1 + 9 = 12
//
//示例 2：
//
//输入： nums = [1,-1,2,-2,3,-3]
//
//输出： 16
//
//解释：
//
//nums 的一种可行重排为 [-3,-1,-2,1,3,2]，该排列在所有可能重排中给出了最大交替得分。
//
//交替得分计算如下：
//
//score = (-3)2 - (-1)2 + (-2)2 - (1)2 + (3)2 - (2)2 = 9 - 1 + 4 - 1 + 9 - 4 = 16
//
//
//
//提示：
//
//1 <= nums.length <= 105
//-4 * 104 <= nums[i] <= 4 * 104

// 其实就是贪心，把大的放前面
func maxAlternatingSum(nums []int) int64 {
	n := len(nums)
	arr := make([]int64, n)
	for i, num := range nums {
		arr[i] = int64(num) * int64(num)
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i] > arr[j]
	})
	mid := (n + 1) / 2
	// [0,mid)一个段
	// [mid,n)一个段
	var res int64
	for i := 0; i < mid; i++ {
		res += arr[i]
	}
	for i := mid; i < n; i++ {
		res -= arr[i]
	}
	return res
}
