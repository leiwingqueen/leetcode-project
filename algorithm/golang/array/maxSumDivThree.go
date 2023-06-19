package array

import "sort"

// 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
//
//
//
//示例 1：
//
//输入：nums = [3,6,5,1,8]
//输出：18
//解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
//示例 2：
//
//输入：nums = [4]
//输出：0
//解释：4 不能被 3 整除，所以无法选出数字，返回 0。
//示例 3：
//
//输入：nums = [1,2,3,4,4]
//输出：12
//解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
//
//
//提示：
//
//1 <= nums.length <= 4 * 10^4
//1 <= nums[i] <= 10^4
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/greatest-sum-divisible-by-three
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 贪心，但不是最优解
func maxSumDivThree(nums []int) int {
	var arr1 []int
	var arr2 []int
	res := 0
	sort.Ints(nums)
	for _, num := range nums {
		mod := num % 3
		if mod == 0 {
			res += num
		} else if mod == 1 {
			arr1 = append(arr1, num)
		} else {
			arr2 = append(arr2, num)
		}
	}
	p1, p2 := len(arr1)-1, len(arr2)-1
	for p1 >= 0 && p2 >= 0 {
		res += arr1[p1] + arr2[p2]
		p1--
		p2--
	}
	return res
}

// dp解法，通过
func maxSumDivThree2(nums []int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 3)
	}
	dp[0][nums[0]%3] = nums[0]
	for i := 1; i < n; i++ {
		for j := 0; j < 3; j++ {
			// 不选
			dp[i][j] = dp[i-1][j]
			// 特殊场景，只选一个的场景
			if nums[i]%3 == j && nums[i] > dp[i][j] {
				dp[i][j] = nums[i]
			}
			// 选择
			mod := (j + 3 - nums[i]%3) % 3
			if dp[i-1][mod] > 0 && dp[i-1][mod]+nums[i] > dp[i][j] {
				dp[i][j] = dp[i-1][mod] + nums[i]
			}
		}
	}
	return dp[n-1][0]
}

// dp优化，总算击败99%的用户
func maxSumDivThree3(nums []int) int {
	n := len(nums)
	// dp := make([][]int, n)
	p := make([]int, 3)
	np := make([]int, 3)
	p[nums[0]%3] = nums[0]
	for i := 1; i < n; i++ {
		for j := 0; j < 3; j++ {
			// 不选
			np[j] = p[j]
			// dp[i][j] = dp[i-1][j]
			// 特殊场景，只选一个的场景
			if nums[i]%3 == j && nums[i] > np[j] {
				np[j] = nums[i]
			}
			// 选择
			mod := (j + 3 - nums[i]%3) % 3
			if p[mod] > 0 && p[mod]+nums[i] > np[j] {
				np[j] = p[mod] + nums[i]
			}
		}
		for k := 0; k < 3; k++ {
			p[k] = np[k]
		}
	}
	return p[0]
}
