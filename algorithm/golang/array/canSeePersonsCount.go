package array

import "sort"

// 有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1 。给你以一个整数数组 heights ，每个整数 互不相同，heights[i] 表示第 i 个人的高度。
//
//一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人 矮 。更正式的，第 i 个人能看到第 j 个人的条件是 i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。
//
//请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到 的 人数 。
//
//
//
//示例 1：
//
//
//
//输入：heights = [10,6,8,5,11,9]
//输出：[3,1,2,1,1,0]
//解释：
//第 0 个人能看到编号为 1 ，2 和 4 的人。
//第 1 个人能看到编号为 2 的人。
//第 2 个人能看到编号为 3 和 4 的人。
//第 3 个人能看到编号为 4 的人。
//第 4 个人能看到编号为 5 的人。
//第 5 个人谁也看不到因为他右边没人。
//示例 2：
//
//输入：heights = [5,1,2,3,10]
//输出：[4,1,1,1,0]
//
//
//提示：
//
//n == heights.length
//1 <= n <= 105
//1 <= heights[i] <= 105
//heights 中所有数 互不相同 。

// 暴力解法，时间复杂度O(n^2)
func canSeePersonsCount(heights []int) []int {
	n := len(heights)
	res := make([]int, n)
	for i, h := range heights {
		mx := 0
		for j := i + 1; j < n; j++ {
			if h > mx && heights[j] > mx {
				res[i]++
			}
			if heights[j] > mx {
				mx = heights[j]
			}
		}
	}
	return res
}

// 单调栈？
// 统计一个范围的单调递增的栈的数量
// 答案错误
func canSeePersonsCount2(heights []int) []int {
	n := len(heights)
	res := make([]int, n)
	var st []int
	for i := n - 1; i >= 0; i-- {
		res[i] = len(st)
		for len(st) > 0 && st[len(st)-1] < heights[i] {
			st = st[:len(st)-1]
		}
		st = append(st, heights[i])
	}
	return res
}

func canSeePersonsCount3(heights []int) []int {
	n := len(heights)
	res := make([]int, n)
	var st []int
	for i := n - 1; i >= 0; i-- {
		if len(st) == 0 {
			res[i] = 0
		} else {
			// 在上面基础上要找到<heights[i]的第一个节点
			idx := sort.Search(len(st), func(k int) bool {
				return st[k] < heights[i]
			})
			if idx == len(st) {
				res[i] = 1
			} else {
				if idx == 0 {
					res[i] = len(st)
				} else {
					res[i] = len(st) - idx + 1
				}
			}
		}
		for len(st) > 0 && st[len(st)-1] < heights[i] {
			st = st[:len(st)-1]
		}
		st = append(st, heights[i])
	}
	return res
}
