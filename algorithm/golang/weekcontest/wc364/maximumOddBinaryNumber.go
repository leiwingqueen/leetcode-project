package wc364

func maximumOddBinaryNumber(s string) string {
	n := len(s)
	oneCnt := 0
	for i := 0; i < n; i++ {
		if s[i] == '1' {
			oneCnt++
		}
	}
	res := make([]byte, n)
	for i := 0; i < n; i++ {
		if oneCnt > 1 {
			res[i] = '1'
			oneCnt--
		} else {
			res[i] = '0'
		}
	}
	res[n-1] = '1'
	return string(res)
}
