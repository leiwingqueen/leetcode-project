package wc375

func getGoodIndices(variables [][]int, target int) []int {
	var res []int
	cal := func(a, b, c, m int) int {
		r := 1
		for i := 0; i < b; i++ {
			r = (r * a) % 10
		}
		r2 := 1
		for i := 0; i < c; i++ {
			r2 = (r2 * r) % m
		}
		return r2
	}
	for i, v := range variables {
		a, b, c, m := v[0], v[1], v[2], v[3]
		if cal(a, b, c, m) == target {
			res = append(res, i)
		}
	}
	return res
}
