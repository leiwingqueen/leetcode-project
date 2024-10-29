package wc421

// 好奇怪的写法
func maxScore(nums []int) int64 {
	n := len(nums)
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	lcm := func(a, b int) int {
		g := gcd(a, b)
		return a * b / g
	}
	var res int64
	for i := 0; i < n; i++ {
		g := 0
		for j := 0; j < n; j++ {
			if j != i {
				if g == 0 {
					g = nums[j]
				} else {
					g = gcd(g, nums[j])
				}
			}
		}
		l := 1
		for j := 0; j < n; j++ {
			if j != i {
				l = lcm(l, nums[j])
			}
		}
		res = max(res, int64(l)*int64(g))
	}
	g := nums[0]
	l := nums[0]
	for i := 1; i < n; i++ {
		g = gcd(g, nums[i])
		l = lcm(l, nums[i])
	}
	res = max(res, int64(l)*int64(g))
	return res
}
