package hash

import "math"

func shortestCompletingWord(licensePlate string, words []string) string {
	//统计出现的字母频数
	mp := make([]int, 26)
	//字母的数量
	need := 0
	for _, ch := range licensePlate {
		if ch >= 'a' && ch <= 'z' {
			if mp[ch-'a'] == 0 {
				need++
			}
			mp[ch-'a']++
		} else if ch >= 'A' && ch <= 'Z' {
			if mp[ch-'A'] == 0 {
				need++
			}
			mp[ch-'A']++
		}
	}
	minLen := math.MaxInt32
	res := ""
	for _, word := range words {
		cnt := make([]int, 26)
		satisfy := 0
		for _, ch := range word {
			cnt[ch-'a']++
			if cnt[ch-'a'] == mp[ch-'a'] {
				satisfy++
			}
		}
		if satisfy >= need && len(word) < minLen {
			res = word
			minLen = len(word)
		}
	}
	return res
}
