package math

//给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
//
//计算置位位数 就是二进制表示中 1 的个数。
//
//例如， 21 的二进制表示 10101 有 3 个计算置位。
//
//
//示例 1：
//
//输入：left = 6, right = 10
//输出：4
//解释：
//6 -> 110 (2 个计算置位，2 是质数)
//7 -> 111 (3 个计算置位，3 是质数)
//9 -> 1001 (2 个计算置位，2 是质数)
//10-> 1010 (2 个计算置位，2 是质数)
//共计 4 个计算置位为质数的数字。
//示例 2：
//
//输入：left = 10, right = 15
//输出：5
//解释：
//10 -> 1010 (2 个计算置位, 2 是质数)
//11 -> 1011 (3 个计算置位, 3 是质数)
//12 -> 1100 (2 个计算置位, 2 是质数)
//13 -> 1101 (3 个计算置位, 3 是质数)
//14 -> 1110 (3 个计算置位, 3 是质数)
//15 -> 1111 (4 个计算置位, 4 不是质数)
//共计 5 个计算置位为质数的数字。
//
//
//提示：
//
//1 <= left <= right <= 106
//0 <= right - left <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func countPrimeSetBits(left int, right int) int {
	cnt := 0
	for i := left; i <= right; i++ {
		if prime(bitCount(i)) {
			cnt++
		}
	}
	return cnt
}

func bitCount(num int) int {
	cnt := 0
	for num > 0 {
		cnt += num & 0b1
		num >>= 1
	}
	return cnt
}

func prime(num int) bool {
	if num <= 1 {
		return false
	}
	for i := 2; i < num; i++ {
		if num%i == 0 {
			return false
		}
	}
	return true
}
