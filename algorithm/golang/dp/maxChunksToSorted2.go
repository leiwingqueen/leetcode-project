package dp

import "sort"

func maxChunksToSorted3(arr []int) int {
	tmp := append([]int{}, arr...)
	sort.Ints(tmp)
	res := 0
	mp := make(map[int]int)
	for i, v := range arr {
		mp[tmp[i]]++
		if mp[tmp[i]] == 0 {
			delete(mp, tmp[i])
		}
		mp[v]--
		if mp[v] == 0 {
			delete(mp, v)
		}
		if len(mp) == 0 {
			res++
		}
	}
	return res
}
