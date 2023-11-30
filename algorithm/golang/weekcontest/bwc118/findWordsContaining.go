package bwc118

func findWordsContaining(words []string, x byte) []int {
	contain := func(word string) bool {
		for _, ch := range word {
			if byte(ch) == x {
				return true
			}
		}
		return false
	}
	var res []int
	for i, word := range words {
		if contain(word) {
			res = append(res, i)
		}
	}
	return res
}
