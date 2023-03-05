package wc335

func passThePillow(n int, time int) int {
	k := 1
	dir := 0
	for time > 0 {
		if dir == 0 {
			k++
			if k == n {
				dir = 1
			}
		} else {
			k--
			if k == 1 {
				dir = 0
			}
		}
		time--
	}
	return k
}
