package bwc94

import "math"

// 给你两个数组 arr1 和 arr2 ，它们一开始都是空的。你需要往它们中添加正整数，使它们满足以下条件：
//
//arr1 包含 uniqueCnt1 个 互不相同 的正整数，每个整数都 不能 被 divisor1 整除 。
//arr2 包含 uniqueCnt2 个 互不相同 的正整数，每个整数都 不能 被 divisor2 整除 。
//arr1 和 arr2 中的元素 互不相同 。
//给你 divisor1 ，divisor2 ，uniqueCnt1 和 uniqueCnt2 ，请你返回两个数组中 最大元素 的 最小值 。
//
//
//
//示例 1：
//
//输入：divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
//输出：4
//解释：
//我们可以把前 4 个自然数划分到 arr1 和 arr2 中。
//arr1 = [1] 和 arr2 = [2,3,4] 。
//可以看出两个数组都满足条件。
//最大值是 4 ，所以返回 4 。
//示例 2：
//
//输入：divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
//输出：3
//解释：
//arr1 = [1,2] 和 arr2 = [3] 满足所有条件。
//最大值是 3 ，所以返回 3 。
//示例 3：
//
//输入：divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
//输出：15
//解释：
//最终数组为 arr1 = [1,3,5,7,9,11,13,15] 和 arr2 = [2,6] 。
//上述方案是满足所有条件的最优解。
//
//
//提示：
//
//2 <= divisor1, divisor2 <= 105
//1 <= uniqueCnt1, uniqueCnt2 < 109
//2 <= uniqueCnt1 + uniqueCnt2 <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力解法，必然超时
func minimizeSet(divisor1 int, divisor2 int, uniqueCnt1 int, uniqueCnt2 int) int {
	k := 1
	// 如果能快速算出c1,c2,c3，是否就能直接用二分解决
	c1 := 0
	c2 := 0
	c3 := 0
	for {
		if k%divisor1 != 0 {
			if k%divisor2 != 0 {
				c3++
			} else {
				c1++
			}
		} else {
			if k%divisor2 != 0 {
				c2++
			}
		}
		need1 := 0
		if c1 < uniqueCnt1 {
			need1 = uniqueCnt1 - c1
		}
		need2 := 0
		if c2 < uniqueCnt2 {
			need2 = uniqueCnt2 - c2
		}
		if c3 >= need1+need2 {
			break
		}
		k++
	}
	return k
}

// 二分+数学，确实有点难
func minimizeSet2(divisor1 int, divisor2 int, uniqueCnt1 int, uniqueCnt2 int) int {
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	check := func(k int) bool {
		l1 := k / divisor1
		l2 := k / divisor2
		// 计算最小公倍数
		g := gcd(divisor1, divisor2)
		m := divisor1 * divisor2 / g
		l3 := k / m
		// c1 := k - l1 - l3
		// c2 := k - l2 - l3
		c3 := k - l1 - l2 + l3
		c1 := l2 - l3
		c2 := l1 - l3
		need1 := 0
		if c1 < uniqueCnt1 {
			need1 = uniqueCnt1 - c1
		}
		need2 := 0
		if c2 < uniqueCnt2 {
			need2 = uniqueCnt2 - c2
		}
		return c3 >= need1+need2
	}
	l := 1
	r := math.MaxInt32
	// r := 4
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
