package wc347

func minimumCost(s string) int64 {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(s)
	var res int64
	for i := 1; i < n; i++ {
		if s[i] != s[i-1] {
			res += int64(min(i, n-i))
		}
	}
	return res
}
