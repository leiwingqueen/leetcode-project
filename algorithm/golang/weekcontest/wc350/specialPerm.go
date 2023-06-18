package wc350

import "fmt"

// 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
//
//对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
//请你返回特别排列的总数目，由于答案可能很大，请将它对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//输入：nums = [2,3,6]
//输出：2
//解释：[3,6,2] 和 [2,6,3] 是 nums 两个特别的排列。
//示例 2：
//
//输入：nums = [1,4,3]
//输出：2
//解释：[3,1,4] 和 [4,1,3] 是 nums 两个特别的排列。
//
//
//提示：
//
//2 <= nums.length <= 14
//1 <= nums[i] <= 109

// 超时
func specialPerm(nums []int) int {
	n := len(nums)
	mod := 1_000_000_007
	var dfs func(arr []int, idx int) int
	dfs = func(arr []int, idx int) int {
		if idx == n {
			return 1
		}
		res := 0
		for i := idx; i < n; i++ {
			if idx == 0 || arr[i]%arr[idx-1] == 0 || arr[idx-1]%arr[i] == 0 {
				// swap
				arr[i], arr[idx] = arr[idx], arr[i]
				res = (res + dfs(arr, idx+1)) % mod
				arr[i], arr[idx] = arr[idx], arr[i]
			}
		}
		return res
	}
	return dfs(nums, 0)
}

// 状态压缩
func specialPerm2(nums []int) int {
	n := len(nums)
	mod := 1_000_000_007
	// mem := make(map[int]int)
	var dfs func(state int, pre int) int
	dfs = func(state int, pre int) int {
		if state == 0 {
			return 1
		}
		/*if v, exist := mem[state]; exist {
			return v
		}*/
		res := 0
		for i := 0; i < n; i++ {
			if state&(1<<i) != 0 && (nums[i]%pre == 0 || pre%nums[i] == 0) {
				res = (res + dfs(state^(1<<i), nums[i])) % mod
			}
		}
		// mem[state] = res
		// fmt.Println(fmt.Sprintf("state:%d,pre:%d,res:%d", state, pre, res))
		return res
	}
	return dfs((1<<n)-1, 1)
}

// 状态压缩+记忆，还是超时
func specialPerm3(nums []int) int {
	n := len(nums)
	mod := 1_000_000_007
	mem := make(map[string]int)
	var dfs func(state int, pre int) int
	dfs = func(state int, pre int) int {
		if state == 0 {
			return 1
		}
		key := fmt.Sprintf("%d#%d", state, pre)
		if v, exist := mem[key]; exist {
			return v
		}
		res := 0
		for i := 0; i < n; i++ {
			if state&(1<<i) != 0 && (nums[i]%pre == 0 || pre%nums[i] == 0) {
				res = (res + dfs(state^(1<<i), nums[i])) % mod
			}
		}
		mem[key] = res
		// fmt.Println(fmt.Sprintf("state:%d,pre:%d,res:%d", state, pre, res))
		return res
	}
	return dfs((1<<n)-1, 1)
}
