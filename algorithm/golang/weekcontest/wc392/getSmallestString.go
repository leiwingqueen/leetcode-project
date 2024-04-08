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
		// fmt.Printf("left:%d\n", left)
		ch1 := s[i] - uint8(left)
		if ch1 < ch {
			ch = ch1
			move = left
		}
		right := min(k, int('a'+26-s[i]))
		// fmt.Printf("right:%d\n", right)
		ch2 := 'a' + (s[i]-'a'+uint8(right))%26
		if ch2 < ch || ch2 == ch && right < move {
			ch = ch2
			move = right
		}
		k -= move
		res[i] = ch
	}
	return string(res)
}
