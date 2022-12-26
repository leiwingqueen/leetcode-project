package twopointer

func countHomogenous(s string) int {
	mod := 1_000_000_007
	n := len(s)
	l := 0
	r := 0
	res := 0
	for r < n {
		for r < n && s[r] == s[l] {
			r++
		}
		res = (res + ((1+r-l)*(r-l)/2)%mod) % mod
		l = r
	}
	return res
}
