package wc464

// 给你一个整数 n。请你计算以下两个值的 最大公约数（GCD）：
//
//sumOdd：前 n 个奇数的总和。
//
//sumEven：前 n 个偶数的总和。
//
//返回 sumOdd 和 sumEven 的 GCD。
//
//
//
//示例 1：
//
//输入： n = 4
//
//输出： 4
//
//解释：
//
//前 4 个奇数的总和 sumOdd = 1 + 3 + 5 + 7 = 16
//前 4 个偶数的总和 sumEven = 2 + 4 + 6 + 8 = 20
//因此，GCD(sumOdd, sumEven) = GCD(16, 20) = 4。
//
//示例 2：
//
//输入： n = 5
//
//输出： 5
//
//解释：
//
//前 5 个奇数的总和 sumOdd = 1 + 3 + 5 + 7 + 9 = 25
//前 5 个偶数的总和 sumEven = 2 + 4 + 6 + 8 + 10 = 30
//因此，GCD(sumOdd, sumEven) = GCD(25, 30) = 5。
//
//
//
//提示：
//
//1 <= n <= 1000

func gcdOfOddEvenSums(n int) int {
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	s1, s2 := 0, 0
	s1 = (1 + 1 + (n-1)*2) * n / 2
	s2 = (2 + 2*(n-1)*2) * n / 2
	return gcd(s1, s2)
}
