package wc313

import "math/bits"

func minimizeXor(num1 int, num2 int) int {
	c2 := bits.OnesCount32(uint32(num2))
	res := 0
	for i := 30; i >= 0; i-- {
		if c2 == 0 {
			break
		}
		if num1&(1<<i) != 0 {
			res |= 1 << i
			c2--
		}
	}
	if c2 > 0 {
		for i := 0; i <= 30; i++ {
			if c2 == 0 {
				break
			}
			if res&(1<<i) == 0 {
				res |= 1 << i
				c2--
			}
		}
	}
	return res
}
