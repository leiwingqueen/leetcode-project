package bwc85

func secondsToRemoveOccurrences(s string) int {
	n := len(s)
	tail := n - 1
	for tail >= 0 && s[tail] == '0' {
		tail--
	}
	if tail < 0 {
		return 0
	}
	res := 0
	for i := tail; i >= 0; i-- {
		if s[i] == '0' {
			res += tail - i
			tail--
		}
	}
	return res
}

func secondsToRemoveOccurrences2(s string) int {
	n := len(s)
	r := make([]byte, n)
	flag := false
	for i := 0; i < n; i++ {
		if s[i] == '0' {
			r[i] = '0'
		} else {
			if i > 0 && s[i-1] == '0' {
				r[i-1] = '1'
				r[i] = '0'
				flag = true
			} else {
				r[i] = '1'
			}
		}
	}
	if !flag {
		return 0
	}
	return secondsToRemoveOccurrences2(string(r)) + 1
}
