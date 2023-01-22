package wc329

func alternateDigitSum(n int) int {
	var arr []int
	for n > 0 {
		arr = append(arr, n%10)
		n /= 10
	}
	i := len(arr) - 1
	positive := true
	res := 0
	for i >= 0 {
		if positive {
			res += arr[i]
		} else {
			res -= arr[i]
		}
		positive = !positive
		i--
	}
	return res
}
