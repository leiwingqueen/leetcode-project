package wc402

// 穷举
func countCompleteDayPairs(hours []int) int {
	res := 0
	for i, v1 := range hours {
		for _, v2 := range hours[i+1:] {
			if (v1+v2)%24 == 0 {
				res++
			}
		}
	}
	return res
}

// 优化解法，24的倍数意味大家取24的模相加刚好取模后是0
// (a+b)%24==0，有(a%24+b%24)%24==0
// a%24+b%24==0||a%24+b%24==24
// a%24=(24-b%24)%24
func countCompleteDayPairs2(hours []int) int64 {
	var res int64
	mp := make([]int, 24)
	for _, h := range hours {
		mod := h % 24
		res += int64(mp[(24-mod)%24])
		mp[mod]++
	}
	return res
}
