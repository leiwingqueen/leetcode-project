package wc501

import "strings"

func countWordOccurrences(chunks []string, queries []string) []int {
	sb := strings.Builder{}
	for i := range chunks {
		sb.WriteString(chunks[i])
	}
	words := strings.Split(sb.String(), " ")
	mp := make(map[string]int)
	for _, word := range words {
		mp[word]++
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		res[i] = mp[query]
	}
	return res
}
