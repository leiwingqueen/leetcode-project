package wc527

func findMismatchedSegments(s1 string, s2 string) [][]string {
	var res [][]string
	l, r := 0, 0
	n := len(s1)
	for r < n {
		for l < n && s1[l] == s2[l] {
			l++
		}
		if l == n {
			break
		}
		r = l
		for r < n && s1[r] != s2[r] {
			r++
		}
		// [l,r)就是
		res = append(res, []string{s1[l:r], s2[l:r]})
		l = r
	}
	return res
}
