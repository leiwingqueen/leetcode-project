package wc349

import "strings"

func smallestString(s string) string {
	n := len(s)
	if n == 1 {
		return string('a' + (s[0]-'a'+25)%26)
	}
	l := 0
	for l < n && s[l] == 'a' {
		l++
	}
	if l == n {
		return s[:n-1] + string('z')
	}
	r := l
	for r < n && s[r] != 'a' {
		r++
	}
	builder := strings.Builder{}
	if l > 0 {
		builder.WriteString(s[:l])
	}
	for i := l; i < r; i++ {
		builder.WriteByte('a' + (s[i]-'a'+25)%26)
	}
	if r < n {
		builder.WriteString(s[r:])
	}
	return builder.String()
}
