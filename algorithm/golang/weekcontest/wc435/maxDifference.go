package wc435

func maxDifference(s string) int {
	cnt := make([]int, 26)
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}
	odd, even := 0, len(s)
	for _, c := range cnt {
		if c == 0 {
			continue
		}
		if c%2 == 1 {
			odd = max(odd, c)
		} else {
			even = min(even, c)
		}
	}
	return odd - even
}
