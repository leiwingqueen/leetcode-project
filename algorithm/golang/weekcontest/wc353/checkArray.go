package wc353

// 2772. 使数组中的所有元素都等于零 显示英文描述
//题目难度Medium
//给你一个下标从 0 开始的整数数组 nums 和一个正整数 k 。
//
//你可以对数组执行下述操作 任意次 ：
//
//从数组中选出长度为 k 的 任一 子数组，并将子数组中每个元素都 减去 1 。
//如果你可以使数组中的所有元素都等于 0 ，返回  true ；否则，返回 false 。
//
//子数组 是数组中的一个非空连续元素序列

// 问题不大，剩下的问题只是超时
func checkArray(nums []int, k int) bool {
	n := len(nums)
	arr := make([]int, n)
	p := 0
	for p < n {
		if arr[p] > nums[p] {
			return false
		}
		if arr[p] == nums[p] {
			p++
		} else {
			diff := nums[p] - arr[p]
			if p+k > n {
				return false
			}
			for i := 0; i < k; i++ {
				arr[p+i] += diff
			}
			p++
		}
	}
	return true
}

// 差分数组
func checkArray2(nums []int, k int) bool {
	n := len(nums)
	arr := make([]int, n)
	p := 0
	cur := 0
	for p < n {
		cur += arr[p]
		if cur > nums[p] {
			return false
		}
		if cur == nums[p] {
			p++
		} else {
			diff := nums[p] - cur
			cur = nums[p]
			if p+k > n {
				return false
			}
			arr[p] += diff
			if p+k < n {
				arr[p+k] -= diff
			}
			p++
		}
	}
	return true
}
