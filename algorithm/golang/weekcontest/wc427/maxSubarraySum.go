package wc427

import "math"

func maxSubarraySum(nums []int, k int) int64 {
	n := len(nums)
	preSum := make([]int64, k)
	// 初始化
	sum := int64(0)
	for i := 1; i < k; i++ {
		sum += int64(nums[i-1])
		preSum[i] = sum
	}
	res := int64(math.MinInt64)
	for i := k; i <= n; i++ {
		sum += int64(nums[i-1])
		d := sum - preSum[i%k]
		res = max(res, d)
		preSum[i%k] = min(preSum[i%k], sum)
	}
	return res
}
