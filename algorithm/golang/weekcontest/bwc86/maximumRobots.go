package bwc86

// 滑窗+单调栈
func maximumRobots(chargeTimes []int, runningCosts []int, budget int64) int {
	n := len(chargeTimes)
	stack := make([]int, 0)
	l := 0
	r := 0
	sum := 0
	for r < n {
		if len(stack) == 0 || stack[len(stack)-1] <= chargeTimes[r] {
			stack = append(stack, chargeTimes[r])
		}
		sum += chargeTimes[r]
	}
}
