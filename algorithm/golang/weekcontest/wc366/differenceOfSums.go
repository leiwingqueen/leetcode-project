package wc366

func differenceOfSums(n int, m int) int {
	res := 0
	for i := 1; i <= n; i++ {
		if i%m != 0 {
			res += i
		} else {
			res -= i
		}
	}
	return res
}
