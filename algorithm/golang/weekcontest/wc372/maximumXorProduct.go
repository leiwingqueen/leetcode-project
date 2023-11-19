package wc372

func maximumXorProduct(a int64, b int64, n int) int {
	mod := 1_000_000_007
	var k int64
	for i := 0; i < n; i++ {
		bit := int64(1 << i)
		if a&bit == b&bit {
			a |= bit
			b |= bit
		} else {
			k |= bit
			if a&bit != 0 {
				a ^= bit
			}
			if b&bit != 0 {
				b ^= bit
			}
		}
	}
	if a > b {
		return (int(a%int64(mod)) * int((b+k)%int64(mod))) % mod
	} else {
		return (int(b%int64(mod)) * int((a+k)%int64(mod))) % mod
	}
}

func maximumXorProduct2(a int64, b int64, n int) int {
	mod := 1_000_000_007
	for i := n - 1; i >= 0; i-- {
		bit := int64(1 << i)
		if a&bit == b&bit {
			a |= bit
			b |= bit
		} else {
			if a >= b {
				if a&bit != 0 {
					a ^= bit
				}
				b |= bit
			} else {
				if b&bit != 0 {
					b ^= bit
				}
				a |= bit
			}
		}
	}
	return int((a % int64(mod) * (b % int64(mod))) % int64(mod))
}
