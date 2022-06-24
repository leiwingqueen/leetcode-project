package string

func countSegments(s string) int {
	cnt := 0
	l := 0
	r := 0
	for r < len(s) {
		for l < len(s) && s[l] == ' ' {
			l++
		}
		r = l
		for r < len(s) && s[r] != ' ' {
			r++
		}
		if r-l > 0 {
			cnt++
		}
		l = r
	}
	return cnt
}
