package wc404

func maxHeightOfTriangle(red int, blue int) int {
	var cal func(start, n int) int
	cal = func(start, n int) int {
		if n < start {
			return 0
		}
		return cal(start+2, n-start) + 1
	}
	cal2 := func(n1, n2 int) int {
		c1 := cal(1, n1)
		c2 := cal(2, n2)
		if c1 == c2 {
			return c1 + c2
		} else if c1 > c2 {
			return c2*2 + 1
		} else {
			return c1 * 2
		}
	}
	return max(cal2(red, blue), cal2(blue, red))
}
