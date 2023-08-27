package wc360

func furthestDistanceFromOrigin(moves string) int {
	lCnt, rCnt, blank := 0, 0, 0
	for _, move := range moves {
		if move == 'L' {
			lCnt++
		} else if move == 'R' {
			rCnt++
		} else {
			blank++
		}
	}
	if lCnt >= rCnt {
		return lCnt - rCnt + blank
	} else {
		return rCnt - lCnt + blank
	}
}
