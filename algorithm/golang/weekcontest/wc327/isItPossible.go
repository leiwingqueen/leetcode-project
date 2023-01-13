package wc327

func isItPossible(word1 string, word2 string) bool {
	mp1 := make([]int, 26)
	mp2 := make([]int, 26)
	cnt1 := 0
	cnt2 := 0
	for _, ch := range word1 {
		mp1[ch-'a']++
		if mp1[ch-'a'] == 1 {
			cnt1++
		}
	}
	for _, ch := range word2 {
		mp2[ch-'a']++
		if mp2[ch-'a'] == 1 {
			cnt2++
		}
	}
	for i := 0; i < 26; i++ {
		if mp1[i] == 0 {
			continue
		}
		for j := 0; j < 26; j++ {
			if mp2[j] == 0 {
				continue
			}
			if i == j {
				if cnt1 == cnt2 {
					return true
				}
			} else {
				c1 := cnt1
				if mp1[i] == 1 {
					c1--
				}
				if mp1[j] == 0 {
					c1++
				}
				c2 := cnt2
				if mp2[j] == 1 {
					c2--
				}
				if mp2[i] == 0 {
					c2++
				}
				if c1 == c2 {
					return true
				}
			}
		}
	}
	return false
}
