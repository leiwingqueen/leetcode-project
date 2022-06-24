package math

import "math"

//给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
//
// 
//
//示例 1：
//
//输入：[3, 2, 1]
//输出：1
//解释：第三大的数是 1 。
//示例 2：
//
//输入：[1, 2]
//输出：2
//解释：第三大的数不存在, 所以返回最大的数 2 。
//示例 3：
//
//输入：[2, 2, 3, 1]
//输出：1
//解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
//此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
// 
//
//提示：
//
//1 <= nums.length <= 104
//-231 <= nums[i] <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/third-maximum-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func thirdMax(nums []int) int {
	max1 := math.MinInt64
	max2 := math.MinInt64
	max3 := math.MinInt64
	for _, num := range nums {
		//过滤掉重复的数字
		if num == max1 || num == max2 || num == max3 {
			continue
		}
		if num > max1 {
			max3 = max2
			max2 = max1
			max1 = num
		} else if num > max2 {
			max3 = max2
			max2 = num
		} else if num > max3 {
			max3 = num
		}
	}
	if max3 == math.MinInt64 {
		return max1
	} else {
		return max3
	}
}
