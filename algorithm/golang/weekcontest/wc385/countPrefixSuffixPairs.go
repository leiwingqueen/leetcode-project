package wc385

func countPrefixSuffixPairs(words []string) int {
	n := len(words)
	checkPre := func(s1, s2 string) bool {
		if len(s1) > len(s2) {
			return false
		}
		for i := 0; i < len(s1); i++ {
			if s1[i] != s2[i] {
				return false
			}
		}
		return true
	}
	checkSuffix := func(s1, s2 string) bool {
		if len(s1) > len(s2) {
			return false
		}
		for i := 0; i < len(s1); i++ {
			if s1[i] != s2[len(s2)-len(s1)+i] {
				return false
			}
		}
		return true
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if checkPre(words[i], words[j]) && checkSuffix(words[i], words[j]) {
				res++
			}
		}
	}
	return res
}
