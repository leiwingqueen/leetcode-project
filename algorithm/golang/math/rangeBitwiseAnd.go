package math

// 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
//
//
//
//示例 1：
//
//输入：left = 5, right = 7
//输出：4
//示例 2：
//
//输入：left = 0, right = 0
//输出：0
//示例 3：
//
//输入：left = 1, right = 2147483647
//输出：0
//
//
//提示：
//
//0 <= left <= right <= 231 - 1

func rangeBitwiseAnd(left int, right int) int {
	if left == 0 {
		return 0
	}
	getBit := func(num int) int {
		for i := 30; i >= 0; i-- {
			if num&(1<<i) != 0 {
				return i
			}
		}
		return -1
	}
	i := getBit(left)
	j := getBit(right)
	if i != j {
		return 0
	}
	// 最大连续相同的bit
	res := 0
	for k := i; k >= 0; k-- {
		if left&(1<<k) != right&(1<<k) {
			break
		}
		res |= left & (1 << k)
	}
	return res
}
