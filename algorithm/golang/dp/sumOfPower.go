package dp

import "sort"

// 给你一个下标从 0 开始的整数数组 nums ，它表示英雄的能力值。如果我们选出一部分英雄，这组英雄的 力量 定义为：
//
//i0 ，i1 ，... ik 表示这组英雄在数组中的下标。那么这组英雄的力量为 max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。
//请你返回所有可能的 非空 英雄组的 力量 之和。由于答案可能非常大，请你将结果对 109 + 7 取余。
//
//
//
//示例 1：
//
//输入：nums = [2,1,4]
//输出：141
//解释：
//第 1 组：[2] 的力量为 22 * 2 = 8 。
//第 2 组：[1] 的力量为 12 * 1 = 1 。
//第 3 组：[4] 的力量为 42 * 4 = 64 。
//第 4 组：[2,1] 的力量为 22 * 1 = 4 。
//第 5 组：[2,4] 的力量为 42 * 2 = 32 。
//第 6 组：[1,4] 的力量为 42 * 1 = 16 。
//第​ ​​​​​​7 组：[2,1,4] 的力量为 42​​​​​​​ * 1 = 16 。
//所有英雄组的力量之和为 8 + 1 + 64 + 4 + 32 + 16 + 16 = 141 。
//示例 2：
//
//输入：nums = [1,1,1]
//输出：7
//解释：总共有 7 个英雄组，每一组的力量都是 1 。所以所有英雄组的力量之和为 7 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/power-of-heroes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 先不考虑溢出的场景，基本正确，证明思路是OK的，可以做下一步处理了
func sumOfPower(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	dp1 := make([]int, n)
	dp1[0] = nums[0]
	for i := 1; i < n; i++ {
		dp1[i] = (dp1[i-1] << 1) + nums[i]
	}
	dp2 := make([]int, n)
	dp2[0] = nums[0] * nums[0] * nums[0]
	for i := 1; i < n; i++ {
		dp2[i] = dp2[i-1] + dp1[i-1]*nums[i]*nums[i] + nums[i]*nums[i]*nums[i]
	}
	return dp2[n-1]
}

// 防止溢出处理
func sumOfPower2(nums []int) int {
	mod := 1_000_000_007
	sort.Ints(nums)
	n := len(nums)
	dp1 := make([]int, n)
	dp1[0] = nums[0]
	for i := 1; i < n; i++ {
		dp1[i] = ((dp1[i-1]<<1)%mod + nums[i]) % mod
	}
	dp2 := make([]int, n)
	dp2[0] = ((nums[0] * nums[0]) % mod * nums[0]) % mod
	for i := 1; i < n; i++ {
		m := (nums[i] * nums[i]) % mod
		dp2[i] = (dp2[i-1] + (dp1[i-1]*m)%mod + (m*nums[i])%mod) % mod
	}
	return dp2[n-1]
}
