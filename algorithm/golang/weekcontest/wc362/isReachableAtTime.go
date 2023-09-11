package wc362

func isReachableAtTime(sx int, sy int, fx int, fy int, t int) bool {
	if sx == fx && sy == fy {
		return t != 1
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	abs := func(a int) int {
		if a > 0 {
			return a
		} else {
			return -a
		}
	}
	dis := abs(fx-sx) + abs(fy-sy) - min(abs(fx-sx), abs(fy-sy))
	return dis <= t
}
