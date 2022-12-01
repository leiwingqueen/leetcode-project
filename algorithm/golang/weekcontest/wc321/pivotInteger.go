package wc321

func pivotInteger(n int) int {
	for i := 1; i <= n; i++ {
		left := i * (i + 1) / 2
		right := n*(n+1)/2 - (i-1)*i/2
		if left == right {
			return i
		}
	}
	return -1
}
