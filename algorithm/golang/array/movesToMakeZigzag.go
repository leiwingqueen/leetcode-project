package array

import "math"

// 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
//
//如果符合下列情况之一，则数组 A 就是 锯齿数组：
//
//每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
//或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
//返回将数组 nums 转换为锯齿数组所需的最小操作次数。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3]
//输出：2
//解释：我们可以把 2 递减到 0，或把 3 递减到 1。
//示例 2：
//
//输入：nums = [9,6,1,6,2]
//输出：4
//
//
//提示：
//
//1 <= nums.length <= 1000
//1 <= nums[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 递归写法，本质上还是贪心
// 既然递归能写，那么改成用迭代也应该没问题
func movesToMakeZigzag(nums []int) int {
	n := len(nums)
	if n == 0 {
		return 0
	}
	var dfs func(idx int) int
	dfs = func(idx int) int {
		if idx >= n {
			return 0
		}
		res := 0
		neighbor := math.MaxInt
		if idx-1 >= 0 && nums[idx-1] < neighbor {
			neighbor = nums[idx-1]
		}
		if idx+1 < n && nums[idx+1] < neighbor {
			neighbor = nums[idx+1]
		}
		if nums[idx] >= neighbor {
			res += nums[idx] - neighbor + 1
		}
		res += dfs(idx + 2)
		return res
	}
	r1 := dfs(0)
	r2 := dfs(1)
	if r1 < r2 {
		return r1
	} else {
		return r2
	}
}
