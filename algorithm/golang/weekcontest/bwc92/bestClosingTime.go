package bwc92

func bestClosingTime(customers string) int {
	n := len(customers)
	idx := 0
	res := 0
	for _, customer := range customers {
		if customer == 1 {
			res++
		}
	}
	window := res
	for i := 0; i < n; i++ {
		if customers[i] == 'Y' {
			window--
		} else {
			window++
		}
		if window < res {
			res = window
			idx = i + 1
		}
	}
	return idx
}
