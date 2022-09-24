package wc264

func countValidWords(sentence string) int {
	var check func(s string) bool
	check = func(s string) bool {
		connect := 0
		dot := 0
		for i := 0; i < len(s); i++ {
			ch := s[i]
			if ch >= '0' && ch <= '9' {
				return false
			} else if ch >= 'a' && ch <= 'z' {

			} else if ch == '-' {
				connect++
				if connect > 1 {
					return false
				}
				if i == 0 || i == len(s)-1 || s[i-1] < 'a' || s[i-1] > 'z' || s[i+1] < 'a' || s[i+1] > 'z' {
					return false
				}
			} else {
				dot++
				if i != len(s)-1 {
					return false
				}
			}
		}
		return true
	}
	l := 0
	r := 0
	res := 0
	for r < len(sentence) {
		for r < len(sentence) && sentence[r] == ' ' {
			r++
		}
		if r == len(sentence) {
			return res
		}
		l = r
		for r < len(sentence) && sentence[r] != ' ' {
			r++
		}
		if check(sentence[l:r]) {
			res++
		}
	}
	return res
}
