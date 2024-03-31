package bwc127

func minimumLevels(possible []int) int {
	sum := 0
	for _, num := range possible {
		if num == 1 {
			sum += 1
		} else {
			sum -= 1
		}
	}
	p1 := 0
	for i, num := range possible[:len(possible)-1] {
		if num == 1 {
			p1++
		} else {
			p1--
		}
		p2 := sum - p1
		if p1 > p2 {
			return i + 1
		}
	}
	return -1
}
