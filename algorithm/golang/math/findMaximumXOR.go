package math

// Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
//
//
//
//Example 1:
//
//Input: nums = [3,10,5,25,2,8]
//Output: 28
//Explanation: The maximum result is 5 XOR 25 = 28.
//Example 2:
//
//Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//Output: 127
//
//
//Constraints:
//
//1 <= nums.length <= 2 * 105
//0 <= nums[i] <= 231 - 1

// 超时
func findMaximumXOR(nums []int) int {
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			s := nums[i] ^ nums[j]
			if s > res {
				res = s
			}
		}
	}
	return res
}
