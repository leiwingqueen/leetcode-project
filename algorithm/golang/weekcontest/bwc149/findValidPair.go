package bwc149

func findValidPair(s string) string {
	cnt := make([]int, 10)
	for i := 0; i < len(s); i++ {
		num := s[i] - '0'
		cnt[num]++
	}
	for i := 1; i < len(s); i++ {
		cur, pre := int(s[i]-'0'), int(s[i-1]-'0')
		if s[i] != s[i-1] && cnt[cur] == cur && cnt[pre] == pre {
			return s[i-1 : i+1]
		}
	}
	return ""
}
