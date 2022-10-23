package array

func mergeAlternately(word1 string, word2 string) string {
	res := make([]byte, 0)
	p1 := 0
	p2 := 0
	for p1 < len(word1) || p2 < len(word2) {
		if p1 < len(word1) {
			res = append(res, word1[p1])
			p1++
		}
		if p2 < len(word2) {
			res = append(res, word2[p2])
			p2++
		}
	}
	return string(res)
}
