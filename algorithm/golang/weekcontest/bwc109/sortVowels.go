package bwc109

import "sort"

func sortVowels(s string) string {
	mp := make(map[byte]bool)
	l := []byte{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
	for _, b := range l {
		mp[b] = true
	}
	n := len(s)
	t := make([]byte, n)
	arr := make([]int, 0)
	for i := 0; i < n; i++ {
		t[i] = s[i]
		if mp[s[i]] {
			arr = append(arr, i)
		}
	}
	if len(arr) > 0 {
		sort.Slice(arr, func(i, j int) bool {
			return s[arr[i]] < s[arr[j]]
		})
		j := 0
		for i := 0; i < n; i++ {
			if mp[s[i]] {
				t[i] = s[arr[j]]
				j++
			}
		}
	}
	return string(t)
}
