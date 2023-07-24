package wc355

import "strings"

func splitWordsBySeparator(words []string, separator byte) []string {
	res := make([]string, 0)
	for _, word := range words {
		split := strings.Split(word, string(separator))
		for _, s := range split {
			if s != "" {
				res = append(res, s)
			}
		}
	}
	return res
}
