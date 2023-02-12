package wc332

import "sort"

// 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
//
//如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
//
//0 <= i < j < n，且
//lower <= nums[i] + nums[j] <= upper
//
//
//示例 1：
//
//输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
//输出：6
//解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
//示例 2：
//
//输入：nums = [1,7,9,2,5], lower = 11, upper = 11
//输出：1
//解释：只有单个公平数对：(2,3) 。
//
//
//提示：
//
//1 <= nums.length <= 105
//nums.length == n
//-109 <= nums[i] <= 109
//-109 <= lower <= upper <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-the-number-of-fair-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func countFairPairs(nums []int, lower int, upper int) int64 {
	n := len(nums)
	sort.Ints(nums)
	cal := func(mx int) int64 {
		l := 0
		r := n - 1
		var res int64
		for l < r {
			for l < r && nums[l]+nums[r] > mx {
				r--
			}
			if l == r {
				break
			}
			res += int64(r - l)
			l++
		}
		return res
	}
	return cal(upper) - cal(lower-1)
}
