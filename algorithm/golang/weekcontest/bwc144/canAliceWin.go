package bwc144

func canAliceWin(n int) bool {
	k := 10
	for {
		if n < k {
			return false
		}
		n -= k
		k--
		if n < k {
			return true
		}
		n -= k
		k--
	}
}
