package wc366

// 贪心，然而并不是最优解
func minOperations(s1 string, s2 string, x int) int {
	n := len(s1)
	p1, p2 := 0, 0
	res := 0
	for p2 < n {
		for p1 < n && s1[p1] == s2[p1] {
			p1++
		}
		if p1 == n {
			return res
		}
		p2 = p1 + 1
		for p2 < n && s1[p2] == s2[p2] {
			p2++
		}
		if p2 == n {
			return -1
		}
		if p2-p1 < x {
			res += p2 - p1
		} else {
			res += x
		}
		p1 = p2 + 1
	}
	return res
}
