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
