package bwc106

// 数据量那么小，暴力即可
func longestSemiRepetitiveSubstring(s string) int {
	n := len(s)
	check := func(l, r int) bool {
		cnt := 0
		for i := l + 1; i <= r; i++ {
			if s[i] == s[i-1] {
				cnt++
			}
		}
		return cnt <= 1
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			if check(i, j) && j-i+1 > res {
				res = j - i + 1
			}
		}
	}
	return res
}
