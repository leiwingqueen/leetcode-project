package dfs

// Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
//
// Example 1:
//
// Input: nums = [4,6,7,7]
// Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// Example 2:
//
// Input: nums = [4,4,3,2,1]
// Output: [[4,4]]
//
// Constraints:
//
// 1 <= nums.length <= 15
// -100 <= nums[i] <= 100

// 这种题目的数据量其实就是直接回溯就好
func findSubsequences(nums []int) [][]int {
	n := len(nums)
	var res [][]int
	var dfs func(p1, p2 int, path []int)
	dfs = func(p1, p2 int, path []int) {
		if p1 == n {
			if p2 >= 2 {
				tmp := make([]int, p2)
				copy(tmp, path[:p2])
				res = append(res, tmp)
			}
			return
		}
		// 不选当前数字
		dfs(p1+1, p2, path)
		// 选择
		if p2 == 0 || nums[p1] >= path[p2-1] {
			path[p2] = nums[p1]
			dfs(p1+1, p2+1, path)
		}
	}
	dfs(0, 0, make([]int, n))
	return res
}
