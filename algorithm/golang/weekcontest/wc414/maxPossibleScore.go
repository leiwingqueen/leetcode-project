package wc414

import (
	"math"
	"sort"
)

// 给你一个整数数组 start 和一个整数 d，代表 n 个区间 [start[i], start[i] + d]。
//
//你需要选择 n 个整数，其中第 i 个整数必须属于第 i 个区间。所选整数的 得分 定义为所选整数两两之间的 最小 绝对差。
//
//返回所选整数的 最大可能得分 。
//
//
//
//示例 1：
//
//输入： start = [6,0,3], d = 2
//
//输出： 4
//
//解释：
//
//可以选择整数 8, 0 和 4 获得最大可能得分，得分为 min(|8 - 0|, |8 - 4|, |0 - 4|)，等于 4。
//
//示例 2：
//
//输入： start = [2,6,13,13], d = 5
//
//输出： 5
//
//解释：
//
//可以选择整数 2, 7, 13 和 18 获得最大可能得分，得分为 min(|2 - 7|, |2 - 13|, |2 - 18|, |7 - 13|, |7 - 18|, |13 - 18|)，等于 5。
//
//
//
//提示：
//
//2 <= start.length <= 105
//0 <= start[i] <= 109
//0 <= d <= 109

// 贪心，错误
func maxPossibleScore(start []int, d int) int {
	n := len(start)
	sort.Ints(start)
	// 找到差距最小的两段
	res := math.MaxInt32
	for i := 1; i < n; i++ {
		dis := start[i] + d - start[i-1]
		res = min(res, dis)
	}
	return res
}

// 二分查找
func maxPossibleScore2(start []int, d int) int {
	n := len(start)
	sort.Ints(start)
	check := func(x int) bool {
		p := start[0]
		for i := 1; i < n; i++ {
			if p+x > start[i]+d {
				return false
			}
			p = max(p+x, start[i])
		}
		return true
	}
	mx := (start[n-1] + d - start[0]) / (n - 1)
	l, r := 0, mx
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
