package wc386

func largestSquareArea(bottomLeft [][]int, topRight [][]int) int64 {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	cal := func(x1, y1, x2, y2 []int) int64 {
		p1 := max(min(x1[1], x2[1])-max(x1[0], x2[0]), 0)
		p2 := max(min(y1[1], y2[1])-max(y1[0], y2[0]), 0)
		length := min(p1, p2)
		return int64(length) * int64(length)
	}
	n := len(bottomLeft)
	var res int64
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			x1 := []int{bottomLeft[i][0], topRight[i][0]}
			y1 := []int{bottomLeft[i][1], topRight[i][1]}
			x2 := []int{bottomLeft[j][0], topRight[j][0]}
			y2 := []int{bottomLeft[j][1], topRight[j][1]}
			c := cal(x1, y1, x2, y2)
			if c > res {
				res = c
			}
		}
	}
	return res
}
