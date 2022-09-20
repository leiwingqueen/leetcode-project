package wc311

func smallestEvenMultiple(n int) int {
	if n%2 == 0 {
		return n
	} else {
		return 2 * n
	}
}
