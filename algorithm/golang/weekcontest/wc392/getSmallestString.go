package wc392

// 贪心
func getSmallestString(s string, k int) string {
	n := len(s)
	var res = []byte(s)
	for i := 0; i < n; i++ {
		if k <= 0 {
			break
		}
		ch := s[i]
		move := 0
		left := min(k, int(s[i]-'a'))
		if s[i]-uint8(left) < ch {
			move = left
		}
		right := min(k, int('a'+26-s[i]))
		if 'a'+(s[i]-'a'+uint8(right))%26 < ch {
			move = right
		}
		k -= move
		res[i] = ch
	}
	return string(res)
}
