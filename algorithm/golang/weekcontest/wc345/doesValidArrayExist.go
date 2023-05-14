package wc345

func doesValidArrayExist(derived []int) bool {
	n := len(derived)
	r1 := make([]int, n)
	for i := 0; i < n-1; i++ {
		r1[i+1] = derived[i] ^ r1[i]
	}
	if r1[n-1]^r1[0] == derived[n-1] {
		return true
	}
	r1[0] = 1
	for i := 0; i < n-1; i++ {
		r1[i+1] = derived[i] ^ r1[i]
	}
	if r1[n-1]^r1[0] == derived[n-1] {
		return true
	}
	return false
}
