package wc130

func maxPointsInsideSquare(points [][]int, s string) int {
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	check := func(d int) (bool, int) {
		mp := make(map[byte]bool)
		cnt := 0
		for i, p := range points {
			x, y := p[0], p[1]
			if abs(x) <= d || abs(y) <= d {
				if mp[s[i]] {
					return false, 0
				}
				mp[s[i]] = true
				cnt++
			}
		}
		return true, cnt
	}
	mx := 0
	for _, p := range points {
		x, y := p[0], p[1]
		mx = max(mx, min(abs(x), abs(y)))
	}
	l, r := 0, mx
	for l < r {
		mid := l + (r-l+1)/2
		if ok, _ := check(mid); ok {
			l = mid
		} else {
			r = mid - 1
		}
	}
	_, res := check(l)
	return res
}
