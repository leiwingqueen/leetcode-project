package wc398

// 车尔尼有一个数组 nums ，它只包含 正 整数，所有正整数的数位长度都 相同 。
//
//两个整数的 数位不同 指的是两个整数 相同 位置上不同数字的数目。
//
//请车尔尼返回 nums 中 所有 整数对里，数位不同之和。
//
//
//
//示例 1：
//
//输入：nums = [13,23,12]
//
//输出：4
//
//解释：
//计算过程如下：
//- 13 和 23 的数位不同为 1 。
//- 13 和 12 的数位不同为 1 。
//- 23 和 12 的数位不同为 2 。
//所以所有整数数对的数位不同之和为 1 + 1 + 2 = 4 。
//
//示例 2：
//
//输入：nums = [10,10,10,10]
//
//输出：0
//
//解释：
//数组中所有整数都相同，所以所有整数数对的数位不同之和为 0 。
//
//
//
//提示：
//
//2 <= nums.length <= 105
//1 <= nums[i] < 109
//nums 中的整数都有相同的数位长度。

// 其实思路还是挺巧妙的
func sumDigitDifferences(nums []int) int64 {
	getSize := func(num int) int {
		size := 0
		for num > 0 {
			num /= 10
			size++
		}
		return size
	}
	size := getSize(nums[0])
	mp := make([][]int, size)
	for i := 0; i < size; i++ {
		mp[i] = make([]int, 10)
	}
	for _, num := range nums {
		for i := 0; i < size; i++ {
			mod := num % 10
			mp[i][mod]++
			num /= 10
		}
	}
	var res int64
	for i := 0; i < size; i++ {
		for j := 0; j < 10; j++ {
			for k := j + 1; k < 10; k++ {
				res += int64(mp[i][j]) * int64(mp[i][k])
			}
		}
	}
	return res
}
