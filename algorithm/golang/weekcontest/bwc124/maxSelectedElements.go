package bwc124

import "sort"

// 给你一个下标从 0 开始只包含 正 整数的数组 nums 。
//
//一开始，你可以将数组中 任意数量 元素增加 至多 1 。
//
//修改后，你可以从最终数组中选择 一个或者更多 元素，并确保这些元素升序排序后是 连续 的。比方说，[3, 4, 5] 是连续的，但是 [3, 4, 6] 和 [1, 1, 2, 3] 不是连续的。
//请你返回 最多 可以选出的元素数目。
//
//
//
//示例 1：
//
//输入：nums = [2,1,5,1,1]
//输出：3
//解释：我们将下标 0 和 3 处的元素增加 1 ，得到结果数组 nums = [3,1,5,2,1] 。
//我们选择元素 [3,1,5,2,1] 并将它们排序得到 [1,2,3] ，是连续元素。
//最多可以得到 3 个连续元素。
//示例 2：
//
//输入：nums = [1,4,7,10]
//输出：1
//解释：我们可以选择的最多元素数目是 1 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 106

// 这个解法肯定超时
func maxSelectedElements(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	check := func(k int) bool {
		for i := 0; i < n; i++ {
			mp := make(map[int]int)
			for _, num := range nums {
				mp[num]++
			}
			expect := nums[i]
			cnt := 0
			for ; cnt < k; cnt++ {
				if _, ok := mp[expect-1]; ok {
					mp[expect-1]--
				} else if _, ok2 := mp[expect]; ok2 {
					mp[expect]--
				} else {
					break
				}
			}
			if cnt == k {
				return true
			}
		}
		return false
	}
	l, r := 0, n
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
