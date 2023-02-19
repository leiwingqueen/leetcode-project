package wc333

// 错误
func minOperations(n int) int {
	res := 0
	for n > 0 {
		if (n & 0b1) == 1 {
			res++
		}
		n >>= 1
	}
	return res
}

func minOperations2(n int) int {
	res := 0
	for n > 0 {
		for n > 0 && (n&0b1) == 0 {
			n >>= 1
		}
		if n == 0 {
			return res
		}
		cnt := 0
		for n > 0 && (n&0b1) == 1 {
			cnt++
			n >>= 1
		}
		if cnt > 1 {
			res++
			n += 0b1
		} else {
			res++
		}
	}
	return res
}
