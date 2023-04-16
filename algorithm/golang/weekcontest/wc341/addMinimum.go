package wc341

func addMinimum(word string) int {
	pattern := "abc"
	p1, p2 := 0, 0
	cnt := 0
	for p1 < len(word) {
		for p1 < len(word) && word[p1] != pattern[p2] {
			p2 = (p2 + 1) % len(pattern)
			cnt++
		}
		p1++
		p2 = (p2 + 1) % len(pattern)
	}
	if p2 == 1 {
		cnt += 2
	} else if p2 == 2 {
		cnt += 1
	}
	return cnt
}
