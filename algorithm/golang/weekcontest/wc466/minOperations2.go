package wc466

func minOperations2(s string) int {
	cnt := make([]int, 26)
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}
	res := 0
	total := 0
	for i := 1; i < 26; i++ {
		cnt[i] += total
		total = cnt[i]
		if cnt[i] > 0 {
			res++
		}
	}
	return res
}
