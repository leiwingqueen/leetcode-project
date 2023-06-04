package wc348

func minimizedStringLength(s string) int {
	mp := make(map[byte]struct{})
	for i := 0; i < len(s); i++ {
		c := s[i]
		mp[c] = struct{}{}
	}
	return len(mp)
}
