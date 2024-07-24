package wc407

func minChanges(n int, k int) int {
	cnt := 0
	for i := 0; i < 31; i++ {
		if n&0x01 != k&0x1 {
			if n&0x1 == 0 {
				return -1
			}
			cnt++
		}
		n >>= 1
		k >>= 1
	}
	return cnt
}
