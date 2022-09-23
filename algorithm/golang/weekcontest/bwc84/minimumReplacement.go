package bwc84

// 给你一个下表从 0 开始的整数数组 nums 。每次操作中，你可以将数组中任何一个元素替换为 任意两个 和为该元素的数字。
//
//比方说，nums = [5,6,7] 。一次操作中，我们可以将 nums[1] 替换成 2 和 4 ，将 nums 转变成 [5,2,4,7] 。
//请你执行上述操作，将数组变成元素按 非递减 顺序排列的数组，并返回所需的最少操作次数。
//
//
//
//示例 1：
//
//输入：nums = [3,9,3]
//输出：2
//解释：以下是将数组变成非递减顺序的步骤：
//- [3,9,3] ，将9 变成 3 和 6 ，得到数组 [3,3,6,3]
//- [3,3,6,3] ，将 6 变成 3 和 3 ，得到数组 [3,3,3,3,3]
//总共需要 2 步将数组变成非递减有序，所以我们返回 2 。
//示例 2：
//
//输入：nums = [1,2,3,4,5]
//输出：0
//解释：数组已经是非递减顺序，所以我们返回 0 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109

// 贪心
func minimumReplacement(nums []int) int64 {
	n := len(nums)
	p := n - 1
	var res int64
	for p >= 0 {
		for p >= 0 && (p == n-1 || nums[p] <= nums[p+1]) {
			p--
		}
		if p < 0 {
			return res
		}
		// 拆分成x个数字
		x := nums[p] / nums[p+1]
		if nums[p]%nums[p+1] != 0 {
			x++
		}
		nums[p] = nums[p] / x
		res += int64(x) - 1
	}
	return res
}
