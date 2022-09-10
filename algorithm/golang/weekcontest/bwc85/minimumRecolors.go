package bwc85

func minimumRecolors(blocks string, k int) int {
	n := len(blocks)
	preSum := make([]int, n+1)
	for i, c := range blocks {
		if c == 'B' {
			preSum[i+1] = preSum[i] + 1
		} else {
			preSum[i+1] = preSum[i]
		}
	}
	res := k
	for i := 0; i <= n-k; i++ {
		t := k - (preSum[i+k] - preSum[i])
		if t < res {
			res = t
		}
	}
	return res
}
