package bwc100

func distMoney(money int, children int) int {
	for k := 0; k <= children; k++ {
		if k*8 > money {
			return k - 1
		}
		c := money - k*8
		l := children - k
		if l == 0 {
			if c == 0 {
				return k
			} else {
				return k - 1
			}
		}
		if c < l {
			return k - 1
		}
		if l == 1 && c == 4 {
			return k - 1
		}
	}
	return children
}
