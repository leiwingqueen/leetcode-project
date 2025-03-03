package wc439

// 给你一个整数数组 nums 和一个整数 k 。
//
//如果整数 x 恰好仅出现在 nums 中的一个大小为 k 的子数组中，则认为 x 是 nums 中的几近缺失（almost missing）整数。
//
//返回 nums 中 最大的几近缺失 整数，如果不存在这样的整数，返回 -1 。
//
//子数组 是数组中的一个连续元素序列。
//
//
//示例 1：
//
//输入：nums = [3,9,2,1,7], k = 3
//
//输出：7
//
//解释：
//
//1 出现在两个大小为 3 的子数组中：[9, 2, 1]、[2, 1, 7]
//2 出现在三个大小为 3 的子数组中：[3, 9, 2]、[9, 2, 1]、[2, 1, 7]
//3 出现在一个大小为 3 的子数组中：[3, 9, 2]
//7 出现在一个大小为 3 的子数组中：[2, 1, 7]
//9 出现在两个大小为 3 的子数组中：[3, 9, 2]、[9, 2, 1]
//返回 7 ，因为它满足题意的所有整数中最大的那个。
//
//示例 2：
//
//输入：nums = [3,9,7,2,1,7], k = 4
//
//输出：3
//
//解释：
//
//1 出现在两个大小为 3 的子数组中：[9, 7, 2, 1]、[7, 2, 1, 7]
//2 出现在三个大小为 3 的子数组中：[3, 9, 7, 2]、[9, 7, 2, 1]、[7, 2, 1, 7]
//3 出现在一个大小为 3 的子数组中：[3, 9, 7, 2]
//7 出现在三个大小为 3 的子数组中：[3, 9, 7, 2]、[9, 7, 2, 1]、[7, 2, 1, 7]
//9 出现在两个大小为 3 的子数组中：[3, 9, 7, 2]、[9, 7, 2, 1]
//返回 3 ，因为它满足题意的所有整数中最大的那个。
//
//示例 3：
//
//输入：nums = [0,0], k = 1
//
//输出：-1
//
//解释：
//
//不存在满足题意的整数。
//
//
//
//提示：
//
//1 <= nums.length <= 50
//0 <= nums[i] <= 50
//1 <= k <= nums.length

// 首先这种数字只能存在数组的头部和尾部，并且需要保证这个数字只出现一次
func largestInteger(nums []int, k int) int {
	n := len(nums)
	if k == n {
		mx := 0
		for i := 0; i < n; i++ {
			mx = max(mx, nums[i])
		}
		return mx
	}
	if k == 1 {
		// 直接选择出现唯一并且最大的即可
		mp := make(map[int]int)
		for i := 0; i < n; i++ {
			mp[nums[i]]++
		}
		res := -1
		for num, v := range mp {
			if v == 1 {
				res = max(res, num)
			}
		}
		return res
	}
	if nums[0] == nums[n-1] {
		return -1
	}
	cnt0, cnt1 := 0, 0
	for i := 0; i < n; i++ {
		if nums[i] == nums[0] {
			cnt0++
		}
		if nums[i] == nums[n-1] {
			cnt1++
		}
	}
	res := -1
	if cnt0 == 1 {
		res = nums[0]
	}
	if cnt1 == 1 {
		res = max(res, nums[n-1])
	}
	return res
}
