package hash

// 给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：
//
//0 <= i < j < nums.length
//nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
//请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//输入：nums = [42,11,1,97]
//输出：2
//解释：两个坐标对为：
// - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
// - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
//示例 2：
//
//输入：nums = [13,10,35,24,76]
//输出：4
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-nice-pairs-in-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func countNicePairs(nums []int) int {
	revert := func(num int) int {
		res := 0
		for num > 0 {
			res = res*10 + num%10
			num /= 10
		}
		return res
	}
	mod := 1_000_000_007
	mp := make(map[int]int)
	res := 0
	for _, num := range nums {
		sub := num - revert(num)
		res = (res + mp[sub]) % mod
		mp[sub]++
	}
	return res
}
