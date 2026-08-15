package wc513

// 给你一个整数数组 nums。
//
//选择 恰好一对 不同下标 i 和 j。该数对的 强度 定义为：
//
//(nums[i] * nums[j]) / gcd(nums[i], nums[j])2
//
//返回所有可能数对中的 最大 强度。
//
//gcd(a, b) 表示 a 和 b 的 最大公约数 。
//
//
//
//示例 1：
//
//输入： nums = [2,3,5]
//
//输出： 15
//
//解释：
//
//选择 i = 1 和 j = 2，得到强度：
//
//(3 * 5) / gcd(3, 5)2 = 15 / 1 = 15，这是所有数对中的最大值。
//
//示例 2：
//
//输入： nums = [4,6,8]
//
//输出： 12
//
//解释：
//
//选择 i = 1 和 j = 2，得到强度：
//
//(6 * 8) / gcd(6, 8)2 = 48 / 4 = 12，这是所有数对中的最大值。
//
//示例 3：
//
//输入： nums = [3,3]
//
//输出： 1
//
//解释：
//
//选择 i = 0 和 j = 1，得到强度：
//
//(3 * 3) / gcd(3, 3)2 = 9 / 9 = 1，这是唯一数对的强度。
//
//
//
//提示：
//
//2 <= nums.length <= 2000
//1 <= nums[i] <= 105

func maxPairStrength(nums []int) int64 {
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	n := len(nums)
	var res int64
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			g := gcd(nums[i], nums[j])
			res = max(res, int64(nums[i]/g)*int64(nums[j]/g))
		}
	}
	return res
}
