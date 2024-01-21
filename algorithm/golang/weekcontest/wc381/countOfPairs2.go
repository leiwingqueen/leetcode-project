package wc381

// 天才想法
func countOfPairs2(n int, x int, y int) []int64 {
	res := make([]int64, n)
	for i := 0; i < n; i++ {
		s := int64(n-i-1) * 2
		res[i] = s
	}
	if x > y {
		x, y = y, x
	}
	if y-x == 1 {
		return res
	}
	for i := y - x; i < n; i++ {
		c := i - (y - x) + 1
		// 从i距离变为i-y-x+1
		res[i-1] -= int64(c) * 2
		res[i-(y-x)] += int64(c) * 2
	}
	return res
}
