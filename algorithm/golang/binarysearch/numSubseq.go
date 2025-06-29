package binarysearch

import (
	"math"
	"sort"
)

// 给你一个整数数组 nums 和一个整数 target 。
//
//请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
//
//由于答案可能很大，请将结果对 109 + 7 取余后返回。
//
//
//
//示例 1：
//
//输入：nums = [3,5,6,7], target = 9
//输出：4
//解释：有 4 个子序列满足该条件。
//[3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
//[3,5] -> (3 + 5 <= 9)
//[3,5,6] -> (3 + 6 <= 9)
//[3,6] -> (3 + 6 <= 9)
//示例 2：
//
//输入：nums = [3,3,6,8], target = 10
//输出：6
//解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
//[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
//示例 3：
//
//输入：nums = [2,3,3,4,6,7], target = 12
//输出：61
//解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
//有效序列总数为（63 - 2 = 61）
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 106
//1 <= target <= 106

// 这样计算可能会溢出，需要使用快速幂来计算
func numSubseq(nums []int, target int) int {
	mod := 1_000_000_007
	n := len(nums)
	sort.Ints(nums)
	res := 0
	for i := 0; i < n; i++ {
		expect := target - nums[i]
		idx := sort.Search(n, func(i int) bool {
			return nums[i] > expect
		})
		if idx >= i {
			// [i,idx-1]这个范围都是满足条件的
			t := int64(math.Pow(2, float64(idx-1-i)))
			res = (res + int(t%int64(mod))) % mod
		}
	}
	return res
}

// 快速幂
func numSubseq2(nums []int, target int) int {
	mod := 1_000_000_007
	var fastPower func(num, k int) int
	fastPower = func(num, k int) int {
		// fmt.Printf("fast power...num:%d,k:%d\n", num, k)
		if k == 0 {
			return 1
		}
		if k%2 == 0 {
			p := fastPower(num, k/2)
			return (p * p) % mod
		} else {
			return (fastPower(num, k-1) * num) % mod
		}
	}
	n := len(nums)
	sort.Ints(nums)
	res := 0
	for i := 0; i < n; i++ {
		expect := target - nums[i]
		idx := sort.Search(n, func(i int) bool {
			return nums[i] > expect
		})
		if idx > i {
			// [i,idx-1]这个范围都是满足条件的
			res = (res + fastPower(2, idx-1-i)) % mod
		}
	}
	return res
}
