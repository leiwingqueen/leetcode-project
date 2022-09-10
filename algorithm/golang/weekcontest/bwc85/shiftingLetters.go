package bwc85

func shiftingLetters(s string, shifts [][]int) string {
	n := len(s)
	diff := make([]int, n)
	diff[0] = int(s[0] - 'a')
	for i := 1; i < n; i++ {
		diff[i] = int(s[i]) - int(s[i-1])
	}
	for _, shift := range shifts {
		from := shift[0]
		to := shift[1]
		dir := shift[2]
		if dir == 0 {
			diff[from] = (diff[from] + 26 - 1) % 26
		} else {
			diff[from] = (diff[from] + 1) % 26
		}
		if to < n-1 {
			if dir == 0 {
				diff[to+1] = (diff[to+1] + 1) % 26
			} else {
				diff[to+1] = (diff[to+1] + 26 - 1) % 26
			}
		}
	}
	r := make([]int, n)
	r[0] = diff[0]
	for i := 1; i < n; i++ {
		r[i] = (r[i-1] + diff[i] + 26) % 26
	}
	res := make([]byte, n)
	for i := 0; i < n; i++ {
		res[i] = byte(r[i] + 'a')
	}
	return string(res)
}
