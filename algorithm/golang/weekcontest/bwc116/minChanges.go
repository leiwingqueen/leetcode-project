package bwc116

func minChanges(s string) int {
	n := len(s)
	res := 0
	for i := 0; i < n; i += 2 {
		if s[i] != s[i+1] {
			res++
		}
	}
	return res
}
