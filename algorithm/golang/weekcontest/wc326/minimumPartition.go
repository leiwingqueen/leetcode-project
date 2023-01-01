package wc326

func minimumPartition(s string, k int) int {
	l := 0
	r := 0
	n := len(s)
	cnt := 0
	for r < n {
		num := 0
		for r < n && num <= k {
			a := int(s[r] - '0')
			num = num*10 + a
			if num > k {
				break
			}
			r++
		}
		if l == r {
			return -1
		}
		cnt++
		l = r
	}
	return cnt
}
