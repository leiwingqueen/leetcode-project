package math

// 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
//
//对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
//
//
//
//示例 1：
//
//输入：n = 12
//输出：true
//解释：12 = 31 + 32
//示例 2：
//
//输入：n = 91
//输出：true
//解释：91 = 30 + 32 + 34
//示例 3：
//
//输入：n = 21
//输出：false
//
//
//提示：
//
//1 <= n <= 107
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func checkPowersOfThree(n int) bool {
	var check func(num int) bool
	check = func(num int) bool {
		if num == 0 || num == 1 {
			return true
		}
		if num == 2 {
			return false
		}
		k := 1
		for k*3 <= num {
			k *= 3
		}
		// 3进制的系数为2，直接返回false即可
		if num/k == 2 {
			return false
		}
		num -= k
		return check(num)
	}
	return check(n)
}
