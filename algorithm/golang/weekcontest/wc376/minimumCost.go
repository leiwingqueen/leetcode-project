package wc376

import "sort"

// 给你一个长度为 n 下标从 0 开始的整数数组 nums 。
//
//你可以对 nums 执行特殊操作 任意次 （也可以 0 次）。每一次特殊操作中，你需要 按顺序 执行以下步骤：
//
//从范围 [0, n - 1] 里选择一个下标 i 和一个 正 整数 x 。
//将 |nums[i] - x| 添加到总代价里。
//将 nums[i] 变为 x 。
//如果一个正整数正着读和反着读都相同，那么我们称这个数是 回文数 。比方说，121 ，2552 和 65756 都是回文数，但是 24 ，46 ，235 都不是回文数。
//
//如果一个数组中的所有元素都等于一个整数 y ，且 y 是一个小于 109 的 回文数 ，那么我们称这个数组是一个 等数数组 。
//
//请你返回一个整数，表示执行任意次特殊操作后使 nums 成为 等数数组 的 最小 总代价。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3,4,5]
//输出：6
//解释：我们可以将数组中所有元素变为回文数 3 得到等数数组，数组变成 [3,3,3,3,3] 需要执行 4 次特殊操作，代价为 |1 - 3| + |2 - 3| + |4 - 3| + |5 - 3| = 6 。
//将所有元素变为其他回文数的总代价都大于 6 。
//示例 2：
//
//输入：nums = [10,12,13,14,15]
//输出：11
//解释：我们可以将数组中所有元素变为回文数 11 得到等数数组，数组变成 [11,11,11,11,11] 需要执行 5 次特殊操作，代价为 |10 - 11| + |12 - 11| + |13 - 11| + |14 - 11| + |15 - 11| = 11 。
//将所有元素变为其他回文数的总代价都大于 11 。
//示例 3 ：
//
//输入：nums = [22,33,22,33,22]
//输出：22
//解释：我们可以将数组中所有元素变为回文数 22 得到等数数组，数组变为 [22,22,22,22,22] 需要执行 2 次特殊操作，代价为 |33 - 22| + |33 - 22| = 22 。
//将所有元素变为其他回文数的总代价都大于 22 。
//
//
//提示：
//
//1 <= n <= 105
//1 <= nums[i] <= 109

func minimumCost(nums []int) int64 {
	check := func(x int) bool {
		var arr []int
		for x > 0 {
			arr = append(arr, x%10)
			x /= 10
		}
		if len(arr) == 0 {
			return true
		}
		p1, p2 := 0, len(arr)-1
		for p1 < p2 {
			if arr[p1] != arr[p2] {
				return false
			}
			p1++
			p2--
		}
		return true
	}
	abs := func(x int) int {
		if x > 0 {
			return x
		} else {
			return -x
		}
	}
	cal := func(x int) int64 {
		var res int64
		for _, num := range nums {
			res += int64(abs(x - num))
		}
		return res
	}
	sort.Ints(nums)
	mid := len(nums) / 2
	left := 0
	for i := nums[mid]; i >= 1; i-- {
		if check(i) {
			left = i
			break
		}
	}
	right := 0
	for i := nums[mid]; i <= 1_000_000_000; i++ {
		if check(i) {
			right = i
			break
		}
	}
	r1 := cal(left)
	r2 := cal(right)
	if r1 < r2 {
		return r1
	} else {
		return r2
	}
}
