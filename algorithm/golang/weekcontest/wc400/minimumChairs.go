package wc400

func minimumChairs(s string) int {
	res := 0
	cnt := 0
	for _, ch := range s {
		if ch == 'E' {
			cnt++
		} else {
			cnt--
		}
		res = max(cnt, res)
	}
	return res
}
