package bwc136

func winningPlayerCount(n int, pick [][]int) int {
	cnt := make([][]int, n)
	mx := 10
	for i := 0; i < n; i++ {
		cnt[i] = make([]int, mx+1)
	}
	for _, p := range pick {
		x, y := p[0], p[1]
		cnt[x][y]++
	}
	res := 0
	for i := 0; i < n; i++ {
		flag := false
		for j := 0; j <= mx; j++ {
			if cnt[i][j] > i {
				flag = true
				break
			}
		}
		if flag {
			res++
		}
	}
	return res
}
