package array

import "strings"

func removeComments(source []string) []string {
	var res []string
	i := 0
	for i < len(source) {
		s := source[i]
		idx1 := strings.Index(s, "//")
		idx2 := strings.Index(s, "/*")
		if idx1 >= 0 && idx2 >= 0 {
			if idx1 < idx2 {
				s = s[0:idx1]
			} else {
				s = s[0:idx2]
			}
		} else if idx1 >= 0 {
			s = s[0:idx1]
		} else if idx2 >= 0 {
			s = s[0:idx2]
		}
		if s != "" {
			res = append(res, s)
		}
		
		if strings.HasPrefix(s, "//") {
			i++
		} else if strings.HasPrefix(s, "/*") {
			p := i
			for p < len(source) && !strings.HasSuffix(source[p], "*/") {
				p++
			}
			i = p + 1
		} else {
			res = append(res, source[i])
			i++
		}
	}
	return res
}
