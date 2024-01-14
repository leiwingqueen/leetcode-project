package wc380

func beautifulIndices(s string, a string, b string, k int) []int {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	n := len(s)
	check := func(i int, str string) bool {
		if n-i < len(str) {
			return false
		}
		for j := 0; j < len(str); j++ {
			if s[i+j] != str[j] {
				return false
			}
		}
		return true
	}
	arr1 := make([]int, n)
	arr2 := make([]int, n)
	for i := 0; i < n; i++ {
		if check(i, a) {
			arr1[i] = 1
		}
		if check(i, b) {
			arr2[i] = 1
		}
	}
	prefix := make([]int, n+1)
	for i := 0; i < n; i++ {
		prefix[i+1] = prefix[i] + arr2[i]
	}
	var res []int
	for i := 0; i < n; i++ {
		if arr1[i] == 1 {
			d := prefix[min(i+k+1, n)] - prefix[max(i-k, 0)]
			if d > 0 {
				res = append(res, i)
			}
		}
	}
	return res
}
