package math

import (
	"math"
	"sort"
)

// 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
//
//给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。由于答案可能非常大，请返回对 109 + 7 取余 后的结果。
//
//子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
//
//
//
//示例 1：
//
//输入：nums = [2,1,3]
//输出：6
//解释：子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
//相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
//宽度之和是 6 。
//示例 2：
//
//输入：nums = [2]
//输出：0
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sum-of-subsequence-widths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func sumSubseqWidths(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	sort.Ints(nums)
	res := 0
	for i, num := range nums {
		// 以num作为最大值的数量
		c1 := int(math.Pow(2, float64(i)))
		// 但是这里会有溢出的问题
		c2 := int(math.Pow(2, float64(n-i-1)))
		// 等价于这样的计算 res += (c1 - c2) * num
		res = (res + ((c1%mod-c2%mod)*num)%mod) % mod
	}
	return res
}

// 溢出问题处理
func sumSubseqWidths2(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	sort.Ints(nums)
	p := make([]int, n)
	p[0] = 1
	for i := 1; i < n; i++ {
		p[i] = (p[i-1] << 1) % mod
	}
	res := 0
	for i, num := range nums {
		// 等价于这样的计算 res += (c1 - c2) * num
		res = (res + ((p[i]-p[n-i-1])*num)%mod) % mod
	}
	return res
}
