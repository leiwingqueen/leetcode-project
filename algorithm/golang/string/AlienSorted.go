package string

func isAlienSorted(words []string, order string) bool {
	orderMap := make(map[byte]int)
	for i := 0; i < len(order); i++ {
		orderMap[order[i]] = i
	}
	compare := func(a string, b string) int {
		p1 := 0
		p2 := 0
		for p1 < len(a) && p2 < len(b) {
			if a[p1] != b[p2] {
				return orderMap[a[p1]] - orderMap[b[p2]]
			}
			p1++
			p2++
		}
		return len(a) - len(b)
	}
	for i := 1; i < len(words); i++ {
		if compare(words[i-1], words[i]) > 0 {
			return false
		}
	}
	return true
}
