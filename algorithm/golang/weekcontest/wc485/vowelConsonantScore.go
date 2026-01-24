package wc485

func vowelConsonantScore(s string) int {
	v, c := 0, 0
	for i := 0; i < len(s); i++ {
		ch := s[i]
		if ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' {
			v++
		} else if ch >= 'a' && ch <= 'z' {
			c++
		}
	}
	if c > 0 {
		return v / c
	} else {
		return 0
	}
}
