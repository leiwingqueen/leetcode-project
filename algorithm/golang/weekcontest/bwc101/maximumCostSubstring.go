package bwc101

func maximumCostSubstring(s string, chars string, vals []int) int {
	v := make([]int, 26)
	for i := 0; i < 26; i++ {
		v[i] = i + 1
	}
	for i, ch := range chars {
		v[ch-'a'] = vals[i]
	}
	res := 0
	min := 0
	sum := 0
	for _, ch := range s {
		sum += v[ch-'a']
		if sum-min > res {
			res = sum - min
		}
		if sum < min {
			min = sum
		}
	}
	return res
}
