package wc397

func findPermutationDifference(s string, t string) int {
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	n := len(s)
	p1, p2 := make([]int, 26), make([]int, 26)
	for i := 0; i < 26; i++ {
		p1[i] = -1
		p2[i] = -1
	}
	for i := 0; i < n; i++ {
		p1[s[i]-'a'] = i
		p2[t[i]-'a'] = i
	}
	res := 0
	for i := 0; i < 26; i++ {
		if p1[i] >= 0 {
			res += abs(p1[i] - p2[i])
		}
	}
	return res
}
