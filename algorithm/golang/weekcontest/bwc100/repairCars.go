package bwc100

import (
	"math"
)

// 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。
//
//同时给你一个整数 cars ，表示总共需要修理的汽车数目。
//
//请你返回修理所有汽车 最少 需要多少时间。
//
//注意：所有机械工可以同时修理汽车。
//
//
//
//示例 1：
//
//输入：ranks = [4,2,3,1], cars = 10
//输出：16
//解释：
//- 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
//- 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
//- 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
//- 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
//16 分钟是修理完所有车需要的最少时间。
//示例 2：
//
//输入：ranks = [5,1,8], cars = 6
//输出：16
//解释：
//- 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
//- 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
//- 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
//16 分钟时修理完所有车需要的最少时间。
//
//
//提示：
//
//1 <= ranks.length <= 105
//1 <= ranks[i] <= 100
//1 <= cars <= 106

// 简单的DP解法，必然超时
func repairCars(ranks []int, cars int) int64 {
	n := len(ranks)
	dp := make([][]int64, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int64, cars+1)
	}
	for i := 1; i <= cars; i++ {
		dp[0][i] = int64(ranks[0]) * int64(i) * int64(i)
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= cars; j++ {
			dp[i][j] = math.MaxInt64
			for k := 0; k <= j; k++ {
				c := Max(dp[i-1][j-k], int64(ranks[i])*int64(k)*int64(k))
				if c < dp[i][j] {
					dp[i][j] = c
				}
			}
		}
	}
	return dp[n-1][cars]
}

// 考虑用二分这道题就变得很简单了
func repairCars2(ranks []int, cars int) int64 {
	check := func(t int64) bool {
		sum := 0
		for _, rank := range ranks {
			d := t / int64(rank)
			sqrt := int(math.Sqrt(float64(d)))
			sum += sqrt
			if sum >= cars {
				return true
			}
		}
		return false
	}
	var l, r int64
	l, r = 1, math.MaxInt64
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func Max(a int64, b int64) int64 {
	if a > b {
		return a
	} else {
		return b
	}
}
