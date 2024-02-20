package bwc124

// 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
//
//选择 nums 中最前面两个元素并且删除它们。
//选择 nums 中最后两个元素并且删除它们。
//选择 nums 中第一个和最后一个元素并且删除它们。
//一次操作的 分数 是被删除元素的和。
//
//在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
//
//请你返回按照上述要求 最多 可以进行的操作次数。
//
//
//
//示例 1：
//
//输入：nums = [3,2,1,2,3,4]
//输出：3
//解释：我们执行以下操作：
//- 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
//- 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
//- 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
//由于 nums 为空，我们无法继续进行任何操作。
//示例 2：
//
//输入：nums = [3,2,6,1,4]
//输出：2
//解释：我们执行以下操作：
//- 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
//- 删除最后两个元素，分数为 1 + 4 = 5 ，nums = [6] 。
//至多进行 2 次操作。
//
//
//提示：
//
//2 <= nums.length <= 2000
//1 <= nums[i] <= 1000

// 感觉直观可以用DFS解决
func maxOperations2(nums []int) int {
	var dfs func(l, r, sum int) int
	dfs = func(l, r, sum int) int {
		if r-l <= 0 {
			return 0
		}
		res := 0
		if sum == 0 || nums[l]+nums[l+1] == sum {
			sub := dfs(l+2, r, nums[l]+nums[l+1]) + 1
			if sub > res {
				res = sub
			}
		}
		if sum == 0 || nums[r-1]+nums[r] == sum {
			sub := dfs(l, r-2, nums[r-1]+nums[r]) + 1
			if sub > res {
				res = sub
			}
		}
		if sum == 0 || nums[l]+nums[r] == sum {
			sub := dfs(l+1, r-1, nums[l]+nums[r]) + 1
			if sub > res {
				res = sub
			}
		}
		return res
	}
	n := len(nums)
	return dfs(0, n-1, 0)
}

// 增加记忆，由于l,r的范围是[0,2000],分别用11个bit存储，然后用10个bit存储sum，刚好32个bit
func maxOperations3(nums []int) int {
	getKey := func(l, r, sum int) int {
		key := (l << 21) | (r << 10) | sum
		return key
	}
	mem := make(map[int]int)
	var dfs func(l, r, sum int) int
	dfs = func(l, r, sum int) int {
		if r-l <= 0 {
			return 0
		}
		if v, ok := mem[getKey(l, r, sum)]; ok {
			return v
		}
		res := 0
		defer func() {
			mem[getKey(l, r, sum)] = res
		}()
		if sum == 0 || nums[l]+nums[l+1] == sum {
			sub := dfs(l+2, r, nums[l]+nums[l+1]) + 1
			if sub > res {
				res = sub
			}
		}
		if sum == 0 || nums[r-1]+nums[r] == sum {
			sub := dfs(l, r-2, nums[r-1]+nums[r]) + 1
			if sub > res {
				res = sub
			}
		}
		if sum == 0 || nums[l]+nums[r] == sum {
			sub := dfs(l+1, r-1, nums[l]+nums[r]) + 1
			if sub > res {
				res = sub
			}
		}
		return res
	}
	n := len(nums)
	return dfs(0, n-1, 0)
}
