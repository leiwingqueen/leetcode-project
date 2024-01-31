package wc382

// 3020. 子集中元素的最大数量 显示英文描述
//通过的用户数1591
//尝试过的用户数2040
//用户总通过次数1691
//用户总提交次数8717
//题目难度Medium
//给你一个 正整数 数组 nums 。
//
//你需要从数组中选出一个满足下述条件的子集：
//
//你可以将选中的元素放置在一个下标从 0 开始的数组中，并使其遵循以下模式：[x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x]（注意，k 可以是任何 非负 的 2 的幂）。例如，[2, 4, 16, 4, 2] 和 [3, 9, 3] 都符合这一模式，而 [2, 4, 8, 4, 2] 则不符合。
//返回满足这些条件的子集中，元素数量的 最大值 。
//
//
//
//示例 1：
//
//输入：nums = [5,4,1,2,2]
//输出：3
//解释：选择子集 {4,2,2} ，将其放在数组 [2,4,2] 中，它遵循该模式，且 22 == 4 。因此答案是 3 。
//示例 2：
//
//输入：nums = [1,3,2,4]
//输出：1
//解释：选择子集 {1}，将其放在数组 [1] 中，它遵循该模式。因此答案是 1 。注意我们也可以选择子集 {2} 、{4} 或 {3} ，可能存在多个子集都能得到相同的答案。
//
//
//提示：
//
//2 <= nums.length <= 105
//1 <= nums[i] <= 109

// 没想到是这样暴力
func maximumLength(nums []int) int {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	cal := func(num int, cnt int) int {
		if num == 1 {
			if cnt%2 == 0 {
				return cnt - 1
			} else {
				return cnt
			}
		} else {
			c := 0
			// 先统计偶数的节点
			expect := num
			for mp[expect] > 1 {
				c += 2
				expect *= expect
			}
			if c == 0 {
				return 1
			}
			if mp[expect] > 0 {
				return c + 1
			} else {
				return c - 1
			}
		}
	}
	res := 0
	for k, v := range mp {
		cnt := cal(k, v)
		if cnt > res {
			res = cnt
		}
	}
	return res
}
