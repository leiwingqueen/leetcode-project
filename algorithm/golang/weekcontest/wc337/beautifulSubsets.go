package wc337

import "sort"

//给你一个由正整数组成的数组 nums 和一个 正 整数 k 。
//
//如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。
//
//返回数组 nums 中 非空 且 美丽 的子集数目。
//
//nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
//
//
//
//示例 1：
//
//输入：nums = [2,4,6], k = 2
//输出：4
//解释：数组 nums 中的美丽子集有：[2], [4], [6], [2, 6] 。
//可以证明数组 [2,4,6] 中只存在 4 个美丽子集。
//示例 2：
//
//输入：nums = [1], k = 1
//输出：1
//解释：数组 nums 中的美丽数组有：[1] 。
//可以证明数组 [1] 中只存在 1 个美丽子集。
//
//
//提示：
//
//1 <= nums.length <= 20
//1 <= nums[i], k <= 1000

func beautifulSubsets(nums []int, k int) int {
	abs := func(x int) int {
		if x < 0 {
			return -x
		}
		return x
	}

	isBeautiful := func(nums []int, mask, k int) bool {
		for i := 0; i < len(nums); i++ {
			for j := i + 1; j < len(nums); j++ {
				if mask&(1<<i) == 0 || mask&(1<<j) == 0 {
					continue
				}
				if abs(nums[i]-nums[j]) == k {
					return false
				}
			}
		}
		return true
	}
	var count int
	for i := 1; i < 1<<len(nums); i++ {
		if isBeautiful(nums, i, k) {
			count++
		}
	}
	return count
}

// 优化解法
func beautifulSubsets2(nums []int, k int) int {
	n := len(nums)
	sort.Ints(nums)
	isBeautiful := func(nums []int, mask, k int) bool {
		for i := 0; i < len(nums); i++ {
			if mask&(1<<i) == 0 {
				continue
			}
			for j := i + 1; j < len(nums); j++ {
				if mask&(1<<j) == 0 {
					continue
				}
				sub := nums[j] - nums[i]
				if sub > k {
					break
				}
				if sub == k {
					return false
				}
			}
		}
		return true
	}
	var count int
	for i := 1; i < 1<<n; i++ {
		if isBeautiful(nums, i, k) {
			count++
		}
	}
	return count
}

func beautifulSubsets3(nums []int, k int) int {
	// 排除掉非美丽子集的数量
	sort.Ints(nums)
	n := len(nums)
	res := 0
	mp := make([]bool, 1001)
	var dfs func(idx int)
	dfs = func(idx int) {
		if idx == n {
			res++
			return
		}
		// 选择当前元素
		if nums[idx]-k < 0 || !mp[nums[idx]-k] {
			mp[nums[idx]] = true
			dfs(idx + 1)
			mp[nums[idx]] = false
		}
		// 不选择当前元素
		dfs(idx + 1)
	}
	dfs(0)
	// 除去空集
	return res - 1
}
