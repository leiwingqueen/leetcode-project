package wc480

func minMoves(balance []int) int64 {
	// 找到负数的下标
	idx := -1
	for i := range balance {
		if balance[i] < 0 {
			idx = i
			break
		}
	}
	if idx < 0 {
		return 0
	}
	// 先尝试从左右两边逐步借
	n := len(balance)
	visit := make([]bool, n)
	visit[idx] = true
	l, r := (idx+n-1)%n, (idx+1)%n
	var res int64
	dis := 1
	for balance[idx] < 0 && (!visit[l] || !visit[r]) {
		if !visit[l] {
			c := min(balance[l], -balance[idx])
			balance[idx] += c
			balance[l] -= c
			res += int64(dis) * int64(c)
			visit[l] = true
		}
		if balance[idx] >= 0 {
			break
		}
		if !visit[r] {
			c := min(balance[r], -balance[idx])
			balance[idx] += c
			balance[r] -= c
			res += int64(dis) * int64(c)
			visit[r] = true
		}
		l = (l + n - 1) % n
		r = (r + 1) % n
		dis++
	}
	if balance[idx] < 0 {
		return -1
	} else {
		return res
	}
}
