package wc313

func commonFactors(a int, b int) int {
	if a > b {
		a, b = b, a
	}
	cnt := 0
	for i := 1; i <= a; i++ {
		if a%i == 0 && b%i == 0 {
			cnt++
		}
	}
	return cnt
}
