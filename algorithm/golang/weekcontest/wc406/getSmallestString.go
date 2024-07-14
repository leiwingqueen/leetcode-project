package wc406

func getSmallestString(s string) string {
	res := []byte(s)
	n := len(s)
	for i := 1; i < n; i++ {
		if s[i] < s[i-1] && (s[i]-'0')%2 == (s[i-1]-'0')%2 {
			res[i], res[i-1] = res[i-1], res[i]
			return string(res)
		}
	}
	return s
}
