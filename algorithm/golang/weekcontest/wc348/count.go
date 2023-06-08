package wc348

import "strconv"

// 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。如果一个整数 x 满足以下条件，我们称它是一个好整数：
//
//num1 <= x <= num2
//min_sum <= digit_sum(x) <= max_sum.
//请你返回好整数的数目。答案可能很大，请返回答案对 109 + 7 取余后的结果。
//
//注意，digit_sum(x) 表示 x 各位数字之和。
//
//
//
//示例 1：
//
//输入：num1 = "1", num2 = "12", min_num = 1, max_num = 8
//输出：11
//解释：总共有 11 个整数的数位和在 1 到 8 之间，分别是 1,2,3,4,5,6,7,8,10,11 和 12 。所以我们返回 11 。
//示例 2：
//
//输入：num1 = "1", num2 = "5", min_num = 1, max_num = 5
//输出：5
//解释：数位和在 1 到 5 之间的 5 个整数分别为 1,2,3,4 和 5 。所以我们返回 5 。
//
//
//提示：
//
//1 <= num1 <= num2 <= 1022
//1 <= min_sum <= max_sum <= 400
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-of-integers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func count(num1 string, num2 string, min_sum int, max_sum int) int {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	var dfs func(num string, mxSum int, n int, ) int
	dfs = func(num string, mxSum int, n int) int {
		if n == 1 {
			if len(num) > 1 {
				return min(10, mxSum)
			} else {
				l, _ := strconv.Atoi(num)
				return min(l+1, mxSum)
			}
		}

	}
}
