package bwc86

func isStrictlyPalindromic(n int) bool {
	check := func(num int, k int) bool {
		arr := make([]int, 0)
		for num > 0 {
			arr = append(arr, num%k)
			num /= k
		}
		l := 0
		r := len(arr) - 1
		for l < r {
			if arr[l] != arr[r] {
				return false
			}
			l++
			r--
		}
		return true
	}
	for i := 2; i <= n-2; i++ {
		if !check(n, i) {
			return false
		}
	}
	return true
}
