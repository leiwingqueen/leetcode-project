package array

// 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
//
//子数组 是数组中 连续 的一部分。
//
//
//
//示例 1：
//
//输入：nums = [1], k = 1
//输出：1
//示例 2：
//
//输入：nums = [1,2], k = 4
//输出：-1
//示例 3：
//
//输入：nums = [2,-1,2], k = 3
//输出：3
//
//
//提示：
//
//1 <= nums.length <= 105
//-105 <= nums[i] <= 105
//1 <= k <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 小优化还是过不了
func shortestSubarray(nums []int, k int) int {
	n := len(nums)
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + nums[i]
	}
	res := -1
	for i := 0; i < n; i++ {
		j := i
		for ; j >= 0; j-- {
			if res >= 0 && i-j+1 >= res {
				break
			}
			//[j,i]是否满足条件
			s := preSum[i+1] - preSum[j]
			if s >= k && (res < 0 || j-i+1 < res) {
				res = i - j + 1
				break
			}
		}
	}
	return res
}
