package wc399

import "fmt"

func compressedString(word string) string {
	var comp []byte
	l, r := 0, 0
	n := len(word)
	for r < n {
		for r < n && word[r] == word[l] && r-l < 9 {
			r++
		}
		s := fmt.Sprintf("%d%c", r-l, word[l])
		comp = append(comp, []byte(s)...)
		l = r
	}
	return string(comp)
}
