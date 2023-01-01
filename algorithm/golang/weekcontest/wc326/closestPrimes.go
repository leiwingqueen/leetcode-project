package wc326

func closestPrimes(left int, right int) []int {
	check := func(num int) bool {
		if num < 2 {
			return false
		}
		for i := 2; i < num; i++ {
			if num%i == 0 {
				return false
			}
		}
		return true
	}
	pre := -1
	res := []int{-1, -1}
	for i := left; i <= right; i++ {
		if check(i) {
			if pre > 0 && (res[0] < 0 || i-pre < (res[1]-res[0])) {
				res[0] = pre
				res[1] = i
			}
			pre = i
		}
	}
	return res
}
