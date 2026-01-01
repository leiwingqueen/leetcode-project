package wc482

import "math"

// 给你一个长度为 n 的整数数组 nums。
//
//请你选出一个下标 i 以分割数组，该下标满足 0 <= i < n - 1。
//
//对于选择的分割下标 i：
//
//令 prefixSum(i) 表示数组前缀的和，即 nums[0] + nums[1] + ... + nums[i]。
//令 suffixMin(i) 表示数组后缀的最小值，即 nums[i + 1], nums[i + 2], ..., nums[n - 1] 中的最小值。
//在下标 i 的 分割得分 定义为：
//
//score(i) = prefixSum(i) - suffixMin(i)
//
//返回所有有效分割下标中 最大 的分割得分。
//
//
//
//示例 1：
//
//输入： nums = [10,-1,3,-4,-5]
//
//输出： 17
//
//解释：
//
//最优的分割下标是 i = 2，score(2) = prefixSum(2) - suffixMin(2) = (10 + (-1) + 3) - (-5) = 17。
//
//示例 2：
//
//输入： nums = [-7,-5,3]
//
//输出： -2
//
//解释：
//
//最优的分割下标是 i = 0，score(0) = prefixSum(0) - suffixMin(0) = (-7) - (-5) = -2。
//
//示例 3：
//
//输入： nums = [1,1]
//
//输出： 0
//
//解释：
//
//唯一有效分割下标是 i = 0，score(0) = prefixSum(0) - suffixMin(0) = 1 - 1 = 0。
//
//
//
//提示：
//
//2 <= nums.length <= 105
//-109​​​​​​​ <= nums[i] <= 109
//

func maximumScore(nums []int) int64 {
	n := len(nums)
	suffixMin := make([]int, n)
	suffixMin[n-1] = nums[n-1]
	for i := n - 2; i >= 0; i-- {
		suffixMin[i] = min(suffixMin[i+1], nums[i])
	}
	res := int64(math.MinInt64)
	sum := int64(0)
	for i := 0; i < n-1; i++ {
		sum += int64(nums[i])
		diff := sum - int64(suffixMin[i+1])
		res = max(res, diff)
	}
	return res
}
