package wc264

import "strings"

func countValidWords(sentence string) int {
	split := strings.Split(sentence, " ")
	res := 0
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
				if i == 0 || i == len(s) || s[i-1] < 'a' || s[i-1] > 'z' || s[i]+1 < 'a' || s[i+1] < 'z' {
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
	for _, s := range split {
		if check(s) {
			res++
		}
	}
	return res
}
