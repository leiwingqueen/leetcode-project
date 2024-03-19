package wc389

import "strings"

func isSubstringPresent(s string) bool {
	revert := func(s string) string {
		var res []byte
		for i := len(s) - 1; i >= 0; i-- {
			res = append(res, s[i])
		}
		return string(res)
	}
	r := revert(s)
	for i := 0; i < len(s)-1; i++ {
		if strings.Contains(s, s[i:i+2]) && strings.Contains(r, s[i:i+2]) {
			return true
		}
	}
	return false
}
