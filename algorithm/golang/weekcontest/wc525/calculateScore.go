package wc525

import "math/bits"

func calculateScore(a int, b int) int {
	s1, s2 := bits.OnesCount(uint(a)), bits.OnesCount(uint(b))
	if s1 >= s2 {
		return s1 * s2
	} else {
		return s2 / s1
	}
}
