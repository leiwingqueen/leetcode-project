package bwc108

func countBlackBlocks(m int, n int, coordinates [][]int) []int64 {
	mp := make(map[int64]bool)
	for _, pos := range coordinates {
		x, y := pos[0], pos[1]
		mp[int64(x<<32)|int64(y)] = true
	}
	res := make([]int64, 5)
	for i := 0; i < m-1; i++ {
		for j := 0; j < n-1; j++ {
			cnt := 0
			if mp[int64(i)<<32|int64(j)] {
				cnt++
			}
			if mp[int64(i)<<32|int64(j+1)] {
				cnt++
			}
			if mp[int64(i+1)<<32|int64(j)] {
				cnt++
			}
			if mp[int64(i+1)<<32|int64(j+1)] {
				cnt++
			}
			res[cnt]++
		}
	}
	return res
}
