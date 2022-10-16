package wc315

func sumOfNumberAndReverse(num int) bool {
	var revert func(num int) int
	revert = func(num int) int {
		res := 0
		for num > 0 {
			res = res*10 + num%10
			num /= 10
		}
		return res
	}
	for i := 0; i <= num; i++ {
		if i+revert(i) == num {
			return true
		}
	}
	return false
}
