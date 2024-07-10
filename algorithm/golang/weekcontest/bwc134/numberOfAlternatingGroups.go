package bwc134

func numberOfAlternatingGroups(colors []int) int {
	n := len(colors)
	res := 0
	for i := range colors {
		x, y, z := colors[i], colors[(i+1)%n], colors[(i+2)%n]
		if x == z && x != y {
			res++
		}
	}
	return res
}
