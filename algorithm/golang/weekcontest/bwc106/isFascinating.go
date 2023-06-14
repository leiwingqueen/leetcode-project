package bwc106

func isFascinating(n int) bool {
	k2 := 2 * n
	k3 := 3 * n
	if k2 >= 1000 || k3 >= 1000 {
		return false
	}
	exist := make([]bool, 10)
	check := func(num int) bool {
		for num > 0 {
			last := num % 10
			if last == 0 || exist[last] {
				return false
			}
			exist[last] = true
			num /= 10
		}
		return true
	}
	return check(n) && check(k2) && check(k3)
}
