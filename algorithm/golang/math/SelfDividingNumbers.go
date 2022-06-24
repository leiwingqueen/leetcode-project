package math

func selfDividingNumbers(left int, right int) []int {
	check := func(n int) bool {
		num := n
		for n > 0 {
			i := n % 10
			if i == 0 || num%i != 0 {
				return false
			}
			n /= 10
		}
		return true
	}
	res := make([]int, 0)
	for i := left; i <= right; i++ {
		if check(i) {
			res = append(res, i)
		}
	}
	return res
}
