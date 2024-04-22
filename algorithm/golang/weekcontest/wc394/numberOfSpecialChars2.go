package wc394

func numberOfSpecialChars2(word string) int {
	litter := make([]int, 26)
	bigger := make([]int, 26)
	for i := 0; i < 26; i++ {
		litter[i] = -1
		bigger[i] = len(word)
	}
	for i, ch := range word {
		if ch >= 'a' && ch <= 'z' {
			litter[ch-'a'] = max(litter[ch-'a'], i)
		} else {
			bigger[ch-'A'] = min(bigger[ch-'A'], i)
		}
	}
	res := 0
	for i := 0; i < 26; i++ {
		if litter[i] >= 0 && bigger[i] < len(word) && litter[i] < bigger[i] {
			res++
		}
	}
	return res
}
