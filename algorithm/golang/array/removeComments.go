package array

import "strings"

func removeComments(source []string) []string {
	var res []string
	block := false
	builder := strings.Builder{}
	for _, s := range source {
		i := 0
		n := len(s)
		for i < n {
			ch := s[i]
			if block {
				if ch == '*' && (i < n-1 && s[i+1] == '/') {
					block = false
					i += 2
				} else {
					i++
				}
			} else {
				if ch == '/' && (i < n-1 && s[i+1] == '/') {
					break
				} else if ch == '/' && (i < n-1 && s[i+1] == '*') {
					// 看下后面是否存在*/
					block = true
					i += 2
				} else {
					builder.WriteByte(s[i])
					i++
				}
			}
		}
		if builder.Len() > 0 && !block {
			res = append(res, builder.String())
			builder.Reset()
		}
	}
	return res
}
