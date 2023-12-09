package bwc119

func removeAlmostEqualCharacters(word string) int {
	n := len(word)
	f0, f1 := 0, 1
	for i := 1; i < n; i++ {
		f0_, f1_ := 0, 0
		if word[i] == word[i-1]+1 || word[i] == word[i-1]-1 || word[i] == word[i-1] {
			f1_ = f0 + 1
			f0_ = f1
		} else {
			f1_ = f0 + 1
			f0_ = f0
		}
		f0, f1 = f0_, f1_
	}
	if f0 < f1 {
		return f0
	} else {
		return f1
	}
}
