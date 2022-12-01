package wc321

func appendCharacters(s string, t string) int {
	p1 := 0
	p2 := 0
	n1 := len(s)
	n2 := len(t)
	for p1 < n1 && p2 < n2 {
		for p1 < n1 && s[p1] != t[p2] {
			p1++
		}
		if p1 == n1 {
			return n2 - p2
		}
		p1++
		p2++
	}
	return n2 - p2
}
