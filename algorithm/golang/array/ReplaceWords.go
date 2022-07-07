package array

import (
	"sort"
	"strings"
)

func replaceWords(dictionary []string, sentence string) string {
	sort.Slice(dictionary, func(i, j int) bool {
		return len(dictionary[i]) < len(dictionary[j])
	})
	words := strings.Split(sentence, " ")
	res := make([]byte, 0)
	for _, word := range words {
		replace := word
		for _, s := range dictionary {
			if strings.HasPrefix(word, s) {
				replace = s
				break
			}
		}
		if len(res) > 0 {
			res = append(res, ' ')
		}
		res = append(res, []byte(replace)...)
	}
	return string(res)
}

//由于题意字典的长度是远小于字典的数量的，可以尝试遍历字典的长度，而不是字典的数量
func replaceWords2(dictionary []string, sentence string) string {
	mxLen := 0
	mp := make(map[string]bool)
	for _, s := range dictionary {
		mp[s] = true
		if len(s) > mxLen {
			mxLen = len(s)
		}
	}
	words := strings.Split(sentence, " ")
	for i, word := range words {
		for j := 1; j <= mxLen && j <= len(word); j++ {
			if mp[word[:j]] {
				words[i] = word[:j]
				break
			}
		}
	}
	return strings.Join(words, " ")
}
