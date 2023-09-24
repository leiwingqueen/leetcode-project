package wc364

func maximumSumOfHeights(maxHeights []int) int64 {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(maxHeights)
	cal := func(i int) int64 {
		var total int64
		pre := maxHeights[i]
		for j := i - 1; j >= 0; j-- {
			h := min(maxHeights[j], pre)
			total += int64(h)
			pre = h
		}
		pre = maxHeights[i]
		for j := i + 1; j < n; j++ {
			h := min(maxHeights[j], pre)
			total += int64(h)
			pre = h
		}
		return total + int64(maxHeights[i])
	}
	var res int64
	for i := 0; i < n; i++ {
		h := cal(i)
		if h > res {
			res = h
		}
	}
	return res
}

// 单调栈可以优化
func maximumSumOfHeights2(maxHeights []int) int64 {
	n := len(maxHeights)
	left := make([]int64, n)
	var stack []int
	var sum int64
	for i := 0; i < n; i++ {
		for len(stack) > 0 && maxHeights[stack[len(stack)-1]] > maxHeights[i] {
			stack = stack[:len(stack)-1]
		}
		cnt := i
		if len(stack) > 0 {
			cnt = i - stack[len(stack)-1] - 1
		}
		sum += int64(cnt) * int64(maxHeights[i])
		stack = append(stack, i)
		sum += int64(maxHeights[i])
		left[i] = sum
	}
	stack = stack[:0]
	right := make([]int64, n)
	sum = 0
	for i := n - 1; i >= 0; i-- {
		for len(stack) > 0 && maxHeights[stack[len(stack)-1]] > maxHeights[i] {
			stack = stack[:len(stack)-1]
		}
		cnt := i
		if len(stack) > 0 {
			cnt = stack[len(stack)-1] - i - 1
		}
		sum += int64(cnt) * int64(maxHeights[i])
		stack = append(stack, i)
		sum += int64(maxHeights[i])
		right[i] = sum
	}
	var res int64
	for i := 0; i < n; i++ {
		h := left[i] + right[i] - int64(maxHeights[i])
		if h > res {
			res = h
		}
	}
	return res
}
