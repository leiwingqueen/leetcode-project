package wc359

func isAcronym(words []string, s string) bool {
	if len(words) != len(s) {
		return false
	}
	for i, word := range words {
		if word[0] != s[i] {
			return false
		}
	}
	return true
}
