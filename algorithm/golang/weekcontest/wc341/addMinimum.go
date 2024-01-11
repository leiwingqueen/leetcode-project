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

func addMinimum2(word string) int {
	expect := 0
	res := 0
	for _, ch := range word {
		res += (int(ch-'a') + 3 - expect) % 3
		expect = (int(ch-'a') + 1) % 3
	}
	expect = 2
	res += (expect + 3 - int(word[len(word)-1]-'a')) % 3
	return res
}
