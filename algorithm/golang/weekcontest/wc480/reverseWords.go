package wc480

import "strings"

func reverseWords(s string) string {
	arr := strings.Split(s, " ")
	var res []byte
	cal := func(word string) int {
		cnt := 0
		for i := range word {
			if word[i] == 'a' || word[i] == 'e' || word[i] == 'i' ||
				word[i] == 'o' || word[i] == 'u' {
				cnt++
			}
		}
		return cnt
	}
	revert := func(word string) string {
		revertStr := []byte(word)
		l, r := 0, len(word)-1
		for l < r {
			revertStr[l], revertStr[r] = revertStr[r], revertStr[l]
			l++
			r--
		}
		return string(revertStr)
	}
	res = append(res, arr[0]...)
	for _, word := range arr[1:] {
		res = append(res, ' ')
		if cal(word) == cal(arr[0]) {
			res = append(res, revert(word)...)
		} else {
			res = append(res, word...)
		}
	}
	return string(res)
}
