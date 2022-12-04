package wc322

import "strings"

func isCircularSentence(sentence string) bool {
	words := strings.Split(sentence, " ")
	for i := 0; i < len(words)-1; i++ {
		w1 := words[i]
		w2 := words[i+1]
		if w1[len(w1)-1] != w2[0] {
			return false
		}
	}
	first := words[0]
	last := words[len(words)-1]
	return last[len(last)-1] == first[0]
}
