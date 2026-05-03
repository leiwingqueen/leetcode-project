package wc499

import "sort"

func sortVowels(s string) string {
	arr := []byte(s)
	var vowelArr []byte
	cnt := map[byte]int{
		'a': 0,
		'e': 0,
		'i': 0,
		'o': 0,
		'u': 0,
	}
	firstSeen := map[byte]int{}
	for i := range arr {
		ch := arr[i]
		if v, ok := cnt[ch]; ok {
			cnt[ch] = v + 1
			vowelArr = append(vowelArr, ch)
			if _, f := firstSeen[ch]; !f {
				firstSeen[ch] = i
			}
		}
	}
	sort.Slice(vowelArr, func(i, j int) bool {
		a, b := vowelArr[i], vowelArr[j]
		if a == b {
			return false
		}
		if cnt[a] != cnt[b] {
			return cnt[a] > cnt[b]
		} else {
			return firstSeen[a] < firstSeen[b]
		}
	})
	idx := 0
	for i := range arr {
		ch := arr[i]
		if _, ok := cnt[ch]; ok {
			arr[i] = vowelArr[idx]
			idx++
		}
	}
	return string(arr)
}
