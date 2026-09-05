package wc517

// 给你一个整数数组 nums。
//
//如果整数 x 在 nums 中的所有出现位置都位于同一个 连续 区间内，则称 x 为 特殊整数。
//
//返回 nums 中 不同 特殊整数的数量。
//
//
//
//示例 1：
//
//输入： nums = [1,2,2,1]
//
//输出： 1
//
//解释：
//
//1 出现在下标 0 和 3，形成了两个分离的区间，因此它不是特殊整数。
//2 在下标 [1, 2] 处形成一个连续区间，因此它是特殊整数。
//因此，共有一个特殊整数。
//
//示例 2：
//
//输入： nums = [3,3,1,2,2,1]
//
//输出： 2
//
//解释：
//
//3 在下标 [0, 1] 处形成一个连续区间，因此它是特殊整数。
//1 出现在下标 2 和 5，形成了两个分离的区间，因此它不是特殊整数。
//2 在下标 [3, 4] 处形成一个连续区间，因此它是特殊整数。
//因此，共有两个特殊整数。
//
//
//
//提示：
//
//1 <= nums.length <= 100
//1 <= nums[i] <= 100

func countSpecialIntegers(nums []int) int {
	seen := make(map[int]bool)
	match := make(map[int]bool)
	n := len(nums)
	l, r := 0, 0
	for r < n {
		lNum := nums[l]
		if !seen[lNum] {
			seen[lNum] = true
			match[lNum] = true
			for r < n && nums[r] == lNum {
				r++
			}
			l = r
		} else {
			match[nums[r]] = false
			r++
			l = r
		}
	}
	res := 0
	for _, m := range match {
		if m {
			res++
		}
	}
	return res
}
