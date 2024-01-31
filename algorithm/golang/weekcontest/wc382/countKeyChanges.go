package wc382

func countKeyChanges(s string) int {
	n := len(s)
	cnt := 0
	convert := func(ch byte) int {
		if ch >= 'a' && ch <= 'z' {
			return int(ch - 'a')
		} else {
			return int(ch - 'A')
		}
	}
	for i := 0; i < n; i++ {
		if i > 0 && convert(s[i]) != convert(s[i-1]) {
			cnt++
		}
	}
	return cnt
}
