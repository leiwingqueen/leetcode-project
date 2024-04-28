package wc395

// 给你两个整数 n 和 x 。你需要构造一个长度为 n 的 正整数 数组 nums ，对于所有 0 <= i < n - 1 ，满足 nums[i + 1] 大于 nums[i] ，并且数组 nums 中所有元素的按位 AND 运算结果为 x 。
//
//返回 nums[n - 1] 可能的 最小 值。
//
//
//
//示例 1：
//
//输入：n = 3, x = 4
//
//输出：6
//
//解释：
//
//数组 nums 可以是 [4,5,6] ，最后一个元素为 6 。
//
//示例 2：
//
//输入：n = 2, x = 7
//
//输出：15
//
//解释：
//
//数组 nums 可以是 [7,15] ，最后一个元素为 15 。
//
//
//
//提示：
//
//1 <= n, x <= 108

func minEnd(n int, x int) int64 {
	bit := 0
	var res int64
	res = int64(x)
	n--
	for n > 0 {
		if x&(1<<bit) == 0 {
			// 这里可以填充
			if n&(n&0b1) != 0 {
				res |= 1 << bit
			}
			n >>= 1
		}
		bit++
	}
	return res
}
