package hash

func findWords(words []string) []string {
	//分别代表26的字母所属的行
	mp := []int{1, 2, 2, 1, 0, 1, 1,
		1, 0, 1, 1, 1, 2, 2,
		0, 0, 0, 0, 1, 0,
		0, 2, 0, 2, 0, 2,
	}
	res := make([]string, 0)
	for _, word := range words {
		match := true
		for i := range word {
			if i != 0 {
				idx1 := 0
				if word[i] >= 'A' && word[i] <= 'Z' {
					idx1 = int(word[i] - 'A')
				} else {
					idx1 = int(word[i] - 'a')
				}
				idx2 := 0
				if word[i-1] >= 'A' && word[i-1] <= 'Z' {
					idx2 = int(word[i-1] - 'A')
				} else {
					idx2 = int(word[i-1] - 'a')
				}
				if mp[idx1] != mp[idx2] {
					match = false
					break
				}
			}
		}
		if match {
			res = append(res, word)
		}
	}
	return res
}
