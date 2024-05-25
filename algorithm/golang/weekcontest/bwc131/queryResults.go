package bwc131

func queryResults(limit int, queries [][]int) []int {
	mp := make(map[int]int)
	res := make([]int, len(queries))
	colors := make([]int, limit+1)
	for i, query := range queries {
		x, y := query[0], query[1]
		if colors[x] > 0 {
			mp[colors[x]]--
			if mp[colors[x]] == 0 {
				delete(mp, colors[x])
			}
		}
		mp[y]++
		colors[x] = y
		res[i] = len(mp)
	}
	return res
}
