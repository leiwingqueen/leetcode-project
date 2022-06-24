package greedy

import (
	"leetcode-go/util"
	"math"
)

//给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
//
//如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,4,5]
//输出：true
//解释：任何 i < j < k 的三元组都满足题意
//示例 2：
//
//输入：nums = [5,4,3,2,1]
//输出：false
//解释：不存在满足题意的三元组
//示例 3：
//
//输入：nums = [2,1,5,0,4,6]
//输出：true
//解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
// 
//
//提示：
//
//1 <= nums.length <= 5 * 105
//-231 <= nums[i] <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func increasingTriplet(nums []int) bool {
	left := make([]int, len(nums))
	right := make([]int, len(nums))
	left[0] = math.MaxInt32
	for i := 1; i < len(nums); i++ {
		left[i] = util.Min(left[i-1], nums[i-1])
	}
	right[len(nums)-1] = math.MinInt32
	for i := len(nums) - 2; i >= 0; i-- {
		right[i] = util.Max(right[i+1], nums[i+1])
	}
	for i := 1; i < len(nums)-1; i++ {
		if nums[i] > left[i] && nums[i] < right[i] {
			return true
		}
	}
	return false
}
