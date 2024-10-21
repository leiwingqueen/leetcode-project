package array

import "sort"

// 给你一个整数数组 nums，和一个整数 k 。
//
//对于每个下标 i（0 <= i < nums.length），将 nums[i] 变成 nums[i] + k 或 nums[i] - k 。
//
//nums 的 分数 是 nums 中最大元素和最小元素的差值。
//
//在更改每个下标对应的值之后，返回 nums 的最小 分数 。
//
//
//
//示例 1：
//
//输入：nums = [1], k = 0
//输出：0
//解释：分数 = max(nums) - min(nums) = 1 - 1 = 0 。
//示例 2：
//
//输入：nums = [0,10], k = 2
//输出：6
//解释：将数组变为 [2, 8] 。分数 = max(nums) - min(nums) = 8 - 2 = 6 。
//示例 3：
//
//输入：nums = [1,3,6], k = 3
//输出：3
//解释：将数组变为 [4, 6, 3] 。分数 = max(nums) - min(nums) = 6 - 3 = 3 。
//
//
//提示：
//
//1 <= nums.length <= 104
//0 <= nums[i] <= 104
//0 <= k <= 104

// 最大数和最小数的范围为[min-k,max+k]
// 假设一个数字可能成为最小值，那么仅当这个数组num<min+k，不然最小值至少应该是min+k
// 同理，一个数字可能成为最大值，仅当num>max-k
// 因为我们考虑的数据范围为[min,min+k)，(max-k,max]
func smallestRangeII(nums []int, k int) int {
	n := len(nums)
	sort.Ints(nums)
	res := nums[n-1] - nums[0]
	for i := 0; i < n-1; i++ {
		minNum := min(nums[0]+k, nums[i+1]-k)
		maxNum := max(nums[i]+k, nums[n-1]-k)
		res = min(res, maxNum-minNum)
	}
	return res
}
