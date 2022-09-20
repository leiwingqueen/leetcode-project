package wc311

func longestContinuousSubstring(s string) int {
	l := 0
	r := 0
	res := 0
	for r < len(s) {
		if s[r] == byte(int(s[l])+r-l) {
			r++
		} else {
			if r-l > res {
				res = r - l
			}
			l = r
		}
	}
	if r-l > res {
		res = r - l
	}
	return res
}
