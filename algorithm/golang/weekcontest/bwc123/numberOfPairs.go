package bwc123

func numberOfPairs(points [][]int) int {
	n := len(points)
	res := 0
	check := func(i, j int) bool {
		x1, y1 := points[i][0], points[i][1]
		x2, y2 := points[j][0], points[j][1]
		if x1 > x2 || y1 < y2 {
			return false
		}
		if x1 == x2 && y1 <= y2 {
			return false
		}
		if y1 == y2 && x1 >= x2 {
			return false
		}
		for k := 0; k < n; k++ {
			if k == i || k == j {
				continue
			}
			x3, y3 := points[k][0], points[k][1]
			if x3 >= x1 && x3 <= x2 && y3 >= y2 && y3 <= y1 {
				return false
			}
		}
		return true
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if i == j {
				continue
			}
			if check(i, j) {
				res++
			}
		}
	}
	return res
}
