package wc481

func mirrorDistance(n int) int {
	revert := func(num int) int {
		res := 0
		for num > 0 {
			res = res*10 + num%10
			num = num / 10
		}
		return res
	}
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	return abs(n - revert(n))
}
