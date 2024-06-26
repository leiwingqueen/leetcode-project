package bwc133

// 给你一个二进制数组 nums 。
//
//你可以对数组执行以下操作 任意 次（也可以 0 次）：
//
//选择数组中 任意 一个下标 i ，并将从下标 i 开始一直到数组末尾 所有 元素 反转 。
//反转 一个元素指的是将它的值从 0 变 1 ，或者从 1 变 0 。
//
//请你返回将 nums 中所有元素变为 1 的 最少 操作次数。
//
//
//
//示例 1：
//
//输入：nums = [0,1,1,0,1]
//
//输出：4
//
//解释：
//我们可以执行以下操作：
//
//选择下标 i = 1 执行操作，得到 nums = [0,0,0,1,0] 。
//选择下标 i = 0 执行操作，得到 nums = [1,1,1,0,1] 。
//选择下标 i = 4 执行操作，得到 nums = [1,1,1,0,0] 。
//选择下标 i = 3 执行操作，得到 nums = [1,1,1,1,1] 。
//示例 2：
//
//输入：nums = [1,0,0,0]
//
//输出：1
//
//解释：
//我们可以执行以下操作：
//
//选择下标 i = 1 执行操作，得到 nums = [1,1,1,1] 。
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 1

// 超时，但是思路是对的
func minOperations2(nums []int) int {
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		if nums[i] == 0 {
			for j := i; j < n; j++ {
				nums[j] ^= 1
			}
			res++
		}
	}
	return res
}

func minOperations3(nums []int) int {
	res := 0
	var s1, s2 []int
	for i, num := range nums {
		if num == 0 {
			s1 = append(s1, i)
		} else {
			s2 = append(s2, i)
		}
	}
	for len(s1) > 0 || len(s2) > 0 {
		if len(s1) == 0 {
			break
		}
		if len(s2) == 0 {
			res++
			break
		}
		if s1[0] < s2[0] {
			res++
			s1 = s1[1:]
			s1, s2 = s2, s1
		} else {
			s2 = s2[1:]
		}
	}
	return res
}
