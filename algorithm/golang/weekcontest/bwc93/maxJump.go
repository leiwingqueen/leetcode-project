package bwc93

// 居然过了？这里相当于只是一个贪心算法
func maxJump(stones []int) int {
	n := len(stones)
	check := func(k int) bool {
		visit := make([]bool, n)
		l := 0
		r := 0
		for r < n {
			for r < n && stones[r]-stones[l] <= k {
				r++
			}
			if r-l == 1 {
				return false
			}
			visit[r-1] = true
			l = r - 1
		}
		r = n - 1
		l = r
		for l > 0 {
			for l >= 0 && visit[l] {
				l--
			}
			if l < 0 || stones[r]-stones[l] > k {
				return false
			}
			visit[l] = true
			r = l
		}
		return true
	}
	l := 1
	r := stones[n-1] - stones[0]
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
