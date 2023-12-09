package bwc119

import "math"

func numberOfSets(n int, maxDistance int, roads [][]int) int {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	check := func(state int) bool {
		dis := make([][]int, n)
		for i := 0; i < n; i++ {
			dis[i] = make([]int, n)
			for j := 0; j < n; j++ {
				dis[i][j] = math.MaxInt / 2
			}
			dis[i][i] = 0
		}
		for _, edge := range roads {
			x, y, w := edge[0], edge[1], edge[2]
			if (state&(1<<x)) != 0 && (state&(1<<y)) != 0 {
				dis[x][y] = min(dis[x][y], w)
				dis[y][x] = min(dis[y][x], w)
			}
		}
		for k := 0; k < n; k++ {
			for i := 0; i < n; i++ {
				for j := 0; j < n; j++ {
					dis[i][j] = min(dis[i][j], dis[i][k]+dis[k][j])
				}
			}
		}
		for i := 0; i < n; i++ {
			if state&(1<<i) == 0 {
				continue
			}
			for j := 0; j < n; j++ {
				if state&(1<<j) == 0 {
					continue
				}
				if dis[i][j] > maxDistance {
					return false
				}
			}
		}
		return true
	}
	res := 0
	for i := 0; i < (1 << n); i++ {
		if check(i) {
			res++
		}
	}
	return res
}
