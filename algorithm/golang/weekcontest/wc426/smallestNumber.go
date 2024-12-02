package wc426

func smallestNumber(n int) int {
	k := 1
	for 1<<k-1 < n {
		k++
	}
	return 1<<k - 1
}
