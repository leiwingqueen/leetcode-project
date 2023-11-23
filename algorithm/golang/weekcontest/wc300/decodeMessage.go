package wc300

func decodeMessage(key string, message string) string {
	mp := make(map[byte]byte)
	mp[' '] = ' '
	idx := 0
	for _, ch := range key {
		if ch >= 'a' && ch <= 'z' {
			if _, ok := mp[byte(ch)]; !ok {
				mp[byte(ch)] = byte('a' + idx)
				idx++
			}
		}
	}
	res := make([]byte, len(message))
	for i := 0; i < len(message); i++ {
		ch := message[i]
		res[i] = mp[ch]
	}
	return string(res)
}
