package math

import "math"

//829. 连续整数求和
//给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
//
//
//
//示例 1:
//
//输入: n = 5
//输出: 2
//解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
//示例 2:
//
//输入: n = 9
//输出: 3
//解释: 9 = 4 + 5 = 2 + 3 + 4
//示例 3:
//
//输入: n = 15
//输出: 4
//解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
//
//
//提示:
//
//1 <= n <= 109​​​​​​​
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/consecutive-numbers-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//时间复杂度O(nlogn)
//超时
func consecutiveNumbersSum(n int) int {
	//二分查找，查到以last结尾的总和为sum的连续数组
	check := func(sum int, last int) bool {
		if last > sum {
			return false
		}
		l := 1
		r := last
		for l <= r {
			mid := l + (r-l)/2
			s := (mid + last) * (last - mid + 1) / 2
			if s == sum {
				return true
			} else if s < sum {
				r = mid - 1
			} else {
				l = mid + 1
			}
		}
		return false
	}
	res := 0
	for i := 1; i <= n; i++ {
		if check(n, i) {
			res++
		}
	}
	return res
}

//通过
func consecutiveNumbersSum2(n int) int {
	//二分查找，连续长度为k的总和为sum的连续数组
	check := func(sum int, k int) bool {
		if k > sum {
			return false
		}
		l := 1
		r := sum - k + 1
		for l <= r {
			mid := l + (r-l)/2
			s := (mid + mid + k - 1) * k / 2
			if s == sum {
				return true
			} else if s < sum {
				l = mid + 1
			} else {
				r = mid - 1
			}
		}
		return false
	}
	res := 0
	//关键一步是这里
	for i := 1; i <= int(math.Sqrt(float64(2*n))); i++ {
		if check(n, i) {
			res++
		}
	}
	return res
}
