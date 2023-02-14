package slidewindow

// 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
//
//我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
//
//所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
//
//请你返回「表现良好时间段」的最大长度。
//
//
//
//示例 1：
//
//输入：hours = [9,9,6,0,6,6,9]
//输出：3
//解释：最长的表现良好时间段是 [9,9,6]。
//示例 2：
//
//输入：hours = [6,6,6]
//输出：0
//
//
//提示：
//
//1 <= hours.length <= 104
//0 <= hours[i] <= 16
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-well-performing-interval
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 理解错误
func longestWPI(hours []int) int {
	n := len(hours)
	l := 0
	r := 0
	res := 0
	for r < n {
		if hours[r] >= 8 {
			r++
		} else {
			if r-l > res {
				res = r - l
			}
			r++
			l = r
		}
	}
	if r-l > res {
		res = r - l
	}
	return res
}

// 时间复杂度O(n^2)
func longestWPI2(hours []int) int {
	n := len(hours)
	preSum := make([]int, n+1)
	for i, h := range hours {
		preSum[i+1] = preSum[i]
		if h > 8 {
			preSum[i+1]++
		} else {
			preSum[i+1]--
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := 0; j <= i; j++ {
			if preSum[i+1]-preSum[j] > 0 {
				if i+1-j > res {
					res = i + 1 - j
				}
				break
			}
		}
	}
	return res
}
