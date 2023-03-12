package wc336

func vowelStrings(words []string, left int, right int) int {
	res := 0
	mp := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	check := func(word string) bool {
		return mp[word[0]] && mp[word[len(word)-1]]
	}
	for i := left; i <= right; i++ {
		if check(words[i]) {
			res++
		}
	}
	return res
}
