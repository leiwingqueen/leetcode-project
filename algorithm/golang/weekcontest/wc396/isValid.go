package wc396

func isValid(word string) bool {
	mp := map[byte]bool{
		'a': true,
		'e': true,
		'i': true,
		'o': true,
		'u': true,
		'A': true,
		'E': true,
		'I': true,
		'O': true,
		'U': true,
	}
	c1, c2 := 0, 0
	for _, ch := range word {
		if ch >= '0' && ch <= '9' {

		} else if ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' {
			if mp[byte(ch)] {
				c1++
			} else {
				c2++
			}
		} else {
			return false
		}
	}
	return c1 > 0 && c2 > 0 && len(word) >= 3
}
