package wc310

func partitionString(s string) int {
	cnt := make([]int, 26)
	res := 1
	for i := 0; i < len(s); i++ {
		idx := int(s[i] - 'a')
		if cnt[idx] == 1 {
			res++
			for j := 0; j < 26; j++ {
				cnt[j] = 0
			}
		}
		cnt[idx]++
	}
	return res
}
