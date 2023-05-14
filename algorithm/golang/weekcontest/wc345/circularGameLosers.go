package wc345

func circularGameLosers(n int, k int) []int {
	visit := make([]bool, n)
	pos := 0
	step := k
	for !visit[pos] {
		visit[pos] = true
		pos = (pos + step) % n
		step += k
	}
	var res []int
	for i := 0; i < n; i++ {
		if !visit[i] {
			res = append(res, i+1)
		}
	}
	return res
}
