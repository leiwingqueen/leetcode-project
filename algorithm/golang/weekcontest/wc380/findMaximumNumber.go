package wc380

import (
	"math"
	"math/bits"
)

// 给你一个整数 k 和一个整数 x 。
//
//令 s 为整数 num 的下标从 1 开始的二进制表示。我们说一个整数 num 的 价值 是满足 i % x == 0 且 s[i] 是 设置位 的 i 的数目。
//
//请你返回 最大 整数 num ，满足从 1 到 num 的所有整数的 价值 和小于等于 k 。
//
//注意：
//
//一个整数二进制表示下 设置位 是值为 1 的数位。
//一个整数的二进制表示下标从右到左编号，比方说如果 s == 11100 ，那么 s[4] == 1 且 s[2] == 0 。
//
//
//示例 1：
//
//输入：k = 9, x = 1
//输出：6
//解释：数字 1 ，2 ，3 ，4 ，5 和 6 二进制表示分别为 "1" ，"10" ，"11" ，"100" ，"101" 和 "110" 。
//由于 x 等于 1 ，每个数字的价值分别为所有设置位的数目。
//这些数字的所有设置位数目总数是 9 ，所以前 6 个数字的价值和为 9 。
//所以答案为 6 。
//示例 2：
//
//输入：k = 7, x = 2
//输出：9
//解释：由于 x 等于 2 ，我们检查每个数字的偶数位。
//2 和 3 在二进制表示下的第二个数位为设置位，所以它们的价值和为 2 。
//6 和 7 在二进制表示下的第二个数位为设置位，所以它们的价值和为 2 。
//8 和 9 在二进制表示下的第四个数位为设置位但第二个数位不是设置位，所以它们的价值和为 2 。
//数字 1 ，4 和 5 在二进制下偶数位都不是设置位，所以它们的价值和为 0 。
//10 在二进制表示下的第二个数位和第四个数位都是设置位，所以它的价值为 2 。
//前 9 个数字的价值和为 6 。
//前 10 个数字的价值和为 8，超过了 k = 7 ，所以答案为 9 。
//
//
//提示：
//
//1 <= k <= 1015
//1 <= x <= 8

// 超时
func findMaximumNumber(k int64, x int) int64 {
	check := func(num int64) bool {
		var cnt int64
		for i := int64(1); i <= num; i++ {
			for j := 1; j <= 32; j += x {
				if i&(1<<(j-1)) != 0 {
					cnt++
					if cnt > k {
						return false
					}
				}
			}
		}
		return true
	}
	l, r := int64(0), int64(math.MaxInt64)
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

func findMaximumNumber2(k int64, x int) int64 {
	cal := func(num int64) int {
		cnt := 0
		for i := 0; i < 64; i++ {
			if (i+1)%x == 0 && num&(1<<i) != 0 {
				cnt++
			}
		}
		return cnt
	}
	var num, sum int64
	num, sum = 1, 0
	for {
		c := cal(num)
		if int64(c)+sum > k {
			return num - 1
		}
		sum += int64(c)
		num++
	}
}

// 真的难
func findMaximumNumber3(k int64, x int) int64 {
	calBit := func(num int64, bit int) int64 {
		period := int64(1) << bit
		round := num / period
		res := round * (period / 2)
		if num%period >= period/2 {
			res += num%period - (period / 2) + 1
		}
		return res
	}
	// 计算<=num的价值
	cal := func(num int64) int64 {
		if num == 0 {
			return 0
		}
		var res int64
		size := 64 - bits.LeadingZeros64(uint64(num))
		for i := x; i <= size; i += x {
			res += calBit(num, i)
		}
		return res
	}
	// 二分查找
	l, r := int64(1), (k+1)<<x
	for l < r {
		mid := l + (r-l+1)/2
		if cal(mid) <= k {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
