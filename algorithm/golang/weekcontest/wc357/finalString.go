package wc357

func finalString(s string) string {
	var arr []byte
	revert := func() {
		if len(arr) == 0 {
			return
		}
		l, r := 0, len(arr)-1
		for l < r {
			arr[l], arr[r] = arr[r], arr[l]
			l++
			r--
		}
	}
	for i := 0; i < len(s); i++ {
		if s[i] == 'i' {
			revert()
		} else {
			arr = append(arr, s[i])
		}
	}
	return string(arr)
}
