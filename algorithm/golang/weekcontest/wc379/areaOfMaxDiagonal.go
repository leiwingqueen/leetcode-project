package wc379

func areaOfMaxDiagonal(dimensions [][]int) int {
	res := 0
	mx := 0
	for _, di := range dimensions {
		x, y := di[0], di[1]
		s := x*x + y*y
		if s > mx || (s == mx && x*y > res) {
			mx = s
			res = x * y
		}
	}
	return res
}
