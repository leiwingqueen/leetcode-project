package bwc138

func stringHash(s string, k int) string {
	n := len(s)
	var res []byte
	for i := 0; i < n; i += k {
		sum := 0
		for j := i; j < i+k; j++ {
			sum += int(s[j] - 'a')
		}
		res = append(res, byte('a'+sum%26))
	}
	return string(res)
}
