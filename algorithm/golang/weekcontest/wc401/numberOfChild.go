package wc401

func numberOfChild(n int, k int) int {
	round := k / (n - 1)
	mod := k % (n - 1)
	if round%2 == 0 {
		return mod
	} else {
		return n - 1 - mod
	}
}
