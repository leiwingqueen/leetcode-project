package wc322

import "sort"

func dividePlayers(skill []int) int64 {
	n := len(skill)
	sum := 0
	for _, s := range skill {
		sum += s
	}
	p := n / 2
	if sum%p != 0 {
		return -1
	}
	k := sum / p
	sort.Ints(skill)
	l := 0
	r := n - 1
	var res int64
	for l < r {
		if skill[l]+skill[r] != k {
			return -1
		}
		res += int64(skill[l]) * int64(skill[r])
		l++
		r--
	}
	return res
}
