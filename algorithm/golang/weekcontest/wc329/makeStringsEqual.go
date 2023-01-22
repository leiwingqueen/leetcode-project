package wc329

func makeStringsEqual(s string, target string) bool {
	zero := 0
	one := 0
	for _, num := range s {
		if num == '0' {
			zero++
		} else {
			one++
		}
	}
	for i := range s {
		if s[i] == '0' && s[i] != target[i] {
			if one <= 0 {
				return false
			}
			one++
			zero--
		}
	}
	for i := range s {
		if s[i] == '1' && s[i] != target[i] {
			if one <= 1 {
				return false
			}
			one--
			zero++
		}
	}
	return true
}
