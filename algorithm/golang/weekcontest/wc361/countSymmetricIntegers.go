package wc361

func countSymmetricIntegers(low int, high int) int {
	check := func(num int) bool {
		var arr []int
		for num > 0 {
			arr = append(arr, num%10)
			num /= 10
		}
		if len(arr) == 0 || len(arr)%2 == 1 {
			return false
		}
		l, r := 0, len(arr)-1
		s1, s2 := 0, 0
		for l < r {
			s1 += arr[l]
			s2 += arr[r]
			l++
			r--
		}
		return s1 == s2
	}
	res := 0
	for i := low; i <= high; i++ {
		if check(i) {
			res++
		}
	}
	return res
}
