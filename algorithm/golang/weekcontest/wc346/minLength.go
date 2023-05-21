package wc346

func minLength(s string) int {
	var st []byte
	for i := 0; i < len(s); i++ {
		ch := s[i]
		if (ch == 'B' && len(st) > 0 && st[len(st)-1] == 'A') ||
			(ch == 'D' && len(st) > 0 && st[len(st)-1] == 'C') {
			st = st[:len(st)-1]
		} else {
			st = append(st, ch)
		}
	}
	return len(st)
}
