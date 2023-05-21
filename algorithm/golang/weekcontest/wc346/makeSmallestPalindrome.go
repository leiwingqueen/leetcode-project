package wc346

func makeSmallestPalindrome(s string) string {
	l, r := 0, len(s)-1
	arr := []byte(s)
	for l < r {
		if arr[l] != arr[r] {
			if arr[l] < arr[r] {
				arr[r] = arr[l]
			} else {
				arr[l] = arr[r]
			}
		}
		l++
		r--
	}
	return string(arr)
}
