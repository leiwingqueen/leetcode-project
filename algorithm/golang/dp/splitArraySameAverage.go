package dp

import "fmt"

// 给定你一个整数数组 nums
//
//我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。
//
//如果可以完成则返回true ， 否则返回 false  。
//
//注意：对于数组 arr ,  average(arr) 是 arr 的所有元素除以 arr 长度的和。
//
//
//
//示例 1:
//
//输入: nums = [1,2,3,4,5,6,7,8]
//输出: true
//解释: 我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
//示例 2:
//
//输入: nums = [3,1]
//输出: false
//
//
//提示:
//
//1 <= nums.length <= 30
//0 <= nums[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/split-array-with-same-average
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 勉强通过，击败10%的用户
func splitArraySameAverage(nums []int) bool {
	n := len(nums)
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + nums[i]
	}
	cache := make(map[string]bool)
	var dfs func(m int, k int, l int) bool
	dfs = func(m int, k int, l int) bool {
		if l < 0 {
			return false
		}
		if k == 0 {
			return false
		}
		if m == k {
			return preSum[m] == l
		}
		key := fmt.Sprintf("%d_%d_%d", m, k, l)
		if v, exist := cache[key]; exist {
			return v
		}
		res := dfs(m-1, k, l) || dfs(m-1, k-1, l-nums[m-1])
		cache[key] = res
		return res
	}
	sum := preSum[n]
	for k := 1; k < n; k++ {
		if (sum*k)%n != 0 {
			continue
		}
		l := (sum * k) / n
		for m := k; m <= n; m++ {
			if dfs(m, k, l) {
				return true
			}
		}
	}
	return false
}
