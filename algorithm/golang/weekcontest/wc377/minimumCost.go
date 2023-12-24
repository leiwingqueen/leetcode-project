package wc377

import "math"

func minimumCost(source string, target string, original []byte, changed []byte, cost []int) int64 {
	dis := make([][]int64, 26)
	min := func(a, b int64) int64 {
		if a < b {
			return a
		} else {
			return b
		}
	}
	for i := 0; i < 26; i++ {
		dis[i] = make([]int64, 26)
		for j := 0; j < 26; j++ {
			dis[i][j] = math.MaxInt64 / 2
		}
		dis[i][i] = 0
	}
	floyd := func() {
		n := len(original)
		for i := 0; i < n; i++ {
			x, y, w := original[i]-'a', changed[i]-'a', cost[i]
			dis[x][y] = min(dis[x][y], int64(w))
		}
		for k := 0; k < 26; k++ {
			for i := 0; i < 26; i++ {
				for j := 0; j < 26; j++ {
					dis[i][j] = min(dis[i][j], dis[i][k]+dis[k][j])
				}
			}
		}
	}
	floyd()
	var res int64
	for i, src := range source {
		tar := target[i]
		d := dis[src-'a'][tar-'a']
		if d >= math.MaxInt64/2 {
			return -1
		}
		res += d
	}
	return res
}
