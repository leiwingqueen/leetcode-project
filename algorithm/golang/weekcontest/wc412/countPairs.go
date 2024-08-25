package wc412

// 给你一个正整数数组 nums 。
//
//如果我们执行以下操作 至多一次 可以让两个整数 x 和 y 相等，那么我们称这个数对是 近似相等 的：
//
//选择 x 或者 y  之一，将这个数字中的两个数位交换。
//请你返回 nums 中，下标 i 和 j 满足 i < j 且 nums[i] 和 nums[j] 近似相等 的数对数目。
//
//注意 ，执行操作后一个整数可以有前导 0 。
//
//
//
//示例 1：
//
//输入：nums = [3,12,30,17,21]
//
//输出：2
//
//解释：
//
//近似相等数对包括：
//
//3 和 30 。交换 30 中的数位 3 和 0 ，得到 3 。
//12 和 21 。交换12 中的数位 1 和 2 ，得到 21 。
//示例 2：
//
//输入：nums = [1,1,1,1,1]
//
//输出：10
//
//解释：
//
//数组中的任意两个元素都是近似相等的。
//
//示例 3：
//
//输入：nums = [123,231]
//
//输出：0
//
//解释：
//
//我们无法通过交换 123 或者 321 中的两个数位得到另一个数。
//
//
//
//提示：
//
//2 <= nums.length <= 100
//1 <= nums[i] <= 106

func countPairs(nums []int) int {
	n := len(nums)
	check := func(num1, num2 int) bool {
		diff := 0
		var diff1, diff2 []int
		for num1 > 0 || num2 > 0 {
			if num1%10 != num2%10 {
				diff1 = append(diff1, num1%10)
				diff2 = append(diff2, num2%10)
				diff++
			}
			if diff > 2 {
				return false
			}
			num1 /= 10
			num2 /= 10
		}
		if diff == 0 {
			return true
		} else if diff == 1 {
			return false
		} else {
			return diff1[0] == diff2[1] && diff1[1] == diff2[0]
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(nums[i], nums[j]) {
				res++
			}
		}
	}
	return res
}
