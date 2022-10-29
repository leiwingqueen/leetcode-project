package bwc90

import "sort"

func oddString(words []string) string {
	n := len(words)
	var convert func(word string) []int
	convert = func(word string) []int {
		m := len(word)
		res := make([]int, m-1)
		for j := 0; j < m-1; j++ {
			res[j] = int(word[j+1] - word[j])
		}
		return res
	}
	var compare func(s1 string, s2 string) int
	compare = func(s1 string, s2 string) int {
		a1 := convert(s1)
		a2 := convert(s2)
		m := len(a1)
		for i := 0; i < m; i++ {
			if a1[i] != a2[i] {
				return a1[i] - a2[i]
			}
		}
		return 0
	}
	sort.Slice(words, func(i, j int) bool {
		return compare(words[i], words[j]) <= 0
	})
	for i := 0; i < n-1; i++ {
		if compare(words[i], words[i+1]) != 0 {
			if i == 0 {
				return words[i]
			} else {
				return words[i+1]
			}
		}
	}
	return ""
}
