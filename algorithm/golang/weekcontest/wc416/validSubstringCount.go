package wc416

func validSubstringCount(word1 string, word2 string) int64 {
	n := len(word1)
	target := make([]int, 26)
	for _, ch := range word2 {
		target[ch-'a']++
	}
	source := make([]int, 26)
	check := func() bool {
		for i := 0; i < 26; i++ {
			if source[i] < target[i] {
				return false
			}
		}
		return true
	}
	var res int64
	l, r := 0, 0
	for l < n {
		if !check() {
			if r == n {
				break
			}
			// 右移
			source[word1[r]-'a']++
			r++
		} else {
			// [l,r),[l,r+1),...[l,n)
			res += int64(n - r + 1)
			source[word1[l]-'a']--
			l++
		}
	}
	return res
}
