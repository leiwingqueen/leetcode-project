package wc417

func kthCharacter(k int) byte {
	word := make([]byte, 0, k)
	word = append(word, 'a')
	for len(word) < k {
		size := len(word)
		for i := 0; i < size; i++ {
			next := word[i] + 1
			if next > 'z' {
				next = 'a'
			}
			word = append(word, next)
		}
	}
	return word[k-1]
}
