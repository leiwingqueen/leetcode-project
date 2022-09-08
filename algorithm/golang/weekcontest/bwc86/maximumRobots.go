package bwc86

// 滑窗+单调栈
func maximumRobots(chargeTimes []int, runningCosts []int, budget int64) int {
	n := len(chargeTimes)
	stack := make([]int, 0)
	l := 0
	r := 0
	var sum int64
	cal := func() int64 {
		if l == r {
			return 0
		}
		return int64(chargeTimes[stack[0]]) + int64(r-l)*sum
	}
	res := 0
	for r < n {
		c := cal()
		if c <= budget {
			sum += int64(runningCosts[r])
			for len(stack) > 0 && stack[len(stack)-1] < chargeTimes[r] {
				stack = stack[:len(stack)-1]
			}
			stack = append(stack, r)
			r++
		} else {
			if r-l > res {
				res = r - l
			}
			sum -= int64(runningCosts[l])
			if len(stack) > 0 && stack[0] == l {
				stack = stack[1:]
			}
			l++
		}
	}
	if cal() <= budget && r-l > res {
		res = r - l
	}
	return res
}
