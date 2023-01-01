package wc326

func countDigits(num int) int {
	res := 0
	tmp := num
	for tmp > 0 {
		k := tmp % 10
		if num%k == 0 {
			res++
		}
		tmp /= 10
	}
	return res
}
