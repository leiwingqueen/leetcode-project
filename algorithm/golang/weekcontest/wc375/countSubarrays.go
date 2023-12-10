package wc375

// 给你一个整数数组 nums 和一个 正整数 k 。
//
//请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
//
//子数组是数组中的一个连续元素序列。
//
//
//
//示例 1：
//
//输入：nums = [1,3,2,3,3], k = 2
//输出：6
//解释：包含元素 3 至少 2 次的子数组为：[1,3,2,3]、[1,3,2,3,3]、[3,2,3]、[3,2,3,3]、[2,3,3] 和 [3,3] 。
//示例 2：
//
//输入：nums = [1,4,2,1], k = 3
//输出：0
//解释：没有子数组包含元素 4 至少 3 次。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 106
//1 <= k <= 105

// 找到以坐标i为最大值的的子数组(相同数字的情况以最早出现的数字为准)
// 右边界为<=nums[i]的最后一个数字，左边界为<nums[i]的最后一个数字
// 先尝试暴力
func countSubarrays(nums []int, k int) int64 {
	n := len(nums)
	var res int64
	for i, num := range nums {
		// 左边界[l+1,i]
		l := i - 1
		for l >= 0 && nums[l] < num {
			l--
		}
		// 右边界[r1,r2)
		r1 := i + 1
		c := 0
		for r1 < n && nums[r1] >= num && c < k {
			if nums[r1] == num {
				c++
			}
		}
		if c < k {
			continue
		}
		r2 := r1
		for r2 < n && nums[r2] >= num {
			r2++
		}
		res = int64(i-l) * int64(r2-r1)
	}
	return res
}
