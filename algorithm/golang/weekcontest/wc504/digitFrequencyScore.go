package wc504

func digitFrequencyScore(n int) int {
	res := 0
	for n > 0 {
		res += n % 10
		n /= 10
	}
	return res
}
