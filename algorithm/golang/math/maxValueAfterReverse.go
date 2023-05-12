package math

//给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
//
// 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
//
// 请你找到可行的最大 数组值 。
//
//
//
// 示例 1：
//
// 输入：nums = [2,3,1,5,4]
//输出：10
//解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。
//
//
// 示例 2：
//
// 输入：nums = [2,4,9,24,2,1,10]
//输出：68
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3*10^4
// -10^5 <= nums[i] <= 10^5
//
//
// Related Topics 贪心 数组 数学 👍 131 👎 0

// 时间复杂度O(n^2)，超时
func maxValueAfterReverse(nums []int) int {
	n := len(nums)
	sum := 0
	for i := 0; i < n-1; i++ {
		sum += abs(nums[i] - nums[i+1])
	}
	res := sum
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			// 尝试翻转[i,j]之间的区间
			s := sum
			if i > 0 {
				s += abs(nums[i-1]-nums[j]) - abs(nums[i-1]-nums[i])
			}
			if j < n-1 {
				s += abs(nums[i]-nums[j+1]) - abs(nums[j]-nums[j+1])
			}
			if s > res {
				res = s
			}
		}
	}
	return res
}

func abs(num int) int {
	if num < 0 {
		return -num
	} else {
		return num
	}
}
