package bwc324

func similarPairs(words []string) int {
	n := len(words)
	if n <= 1 {
		return 0
	}
	count := func(word string) int {
		arr := make([]bool, 26)
		for i := 0; i < len(word); i++ {
			arr[word[i]-'a'] = true
		}
		res := 0
		for i := 0; i < 26; i++ {
			if arr[i] {
				res |= 1 << i
			}
		}
		return res
	}
	res := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if count(words[i]) == count(words[j]) {
				res++
			}
		}
	}
	return res
}
