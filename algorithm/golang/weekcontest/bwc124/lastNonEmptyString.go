package bwc124

import "sort"

func lastNonEmptyString(s string) string {
	n := len(s)
	cnt := make([]int, 26)
	lastPos := make([]int, 26)
	for i := 0; i < n; i++ {
		idx := s[i] - 'a'
		cnt[idx]++
		lastPos[idx] = i
	}
	mx := 0
	for i := 0; i < 26; i++ {
		if cnt[i] > mx {
			mx = cnt[i]
		}
	}
	// 只需要保留出现次数最大的字符
	var res []byte
	for i := 0; i < 26; i++ {
		if cnt[i] == mx {
			res = append(res, byte(i+'a'))
		}
	}
	// 根据最后出现的位置排序
	sort.Slice(res, func(i, j int) bool {
		idx1 := res[i] - 'a'
		idx2 := res[j] - 'a'
		return lastPos[idx1] < lastPos[idx2]
	})
	return string(res)
}
