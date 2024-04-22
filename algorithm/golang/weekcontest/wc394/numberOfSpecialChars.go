package wc394

func numberOfSpecialChars(word string) int {
	litter := make([]bool, 26)
	bigger := make([]bool, 26)
	for _, ch := range word {
		if ch >= 'a' && ch <= 'z' {
			litter[ch-'a'] = true
		} else {
			bigger[ch-'A'] = true
		}
	}
	res := 0
	for i := 0; i < 26; i++ {
		if litter[i] && bigger[i] {
			res++
		}
	}
	return res
}
