package bwc81

func countAsterisks(s string) int {
	r := 0
	n := len(s)
	cnt := 0
	for r < n {
		for r < n && s[r] != '|' {
			if s[r] == '*' {
				cnt++
			}
			r++
		}
		if r == n {
			return cnt
		}
		//跳过竖线对
		r++
		for r < n && s[r] != '|' {
			r++
		}
		r++
	}
	return cnt
}
