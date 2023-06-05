package wc348

// 模拟，必然超时
func matrixSumQueries(n int, queries [][]int) int64 {
	matrix := make([][]int, n)
	for i := 0; i < n; i++ {
		matrix[i] = make([]int, n)
	}
	for _, query := range queries {
		t, index, v := query[0], query[1], query[2]
		if t == 0 {
			for i := 0; i < n; i++ {
				matrix[index][i] = v
			}
		} else {
			for i := 0; i < n; i++ {
				matrix[i][index] = v
			}
		}
	}
	var res int64
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			res += int64(matrix[i][j])
		}
	}
	return res
}

// 倒序想不出来啊
func matrixSumQueries2(n int, queries [][]int) int64 {
	rowVis := make([]bool, n)
	colVis := make([]bool, n)
	rowCnt, colCnt := 0, 0
	m := len(queries)
	var res int64
	for i := m - 1; i >= 0; i-- {
		query := queries[i]
		t, index, v := query[0], query[1], query[2]
		if t == 0 {
			if !rowVis[index] {
				rowVis[index] = true
				res += int64(n-colCnt) * int64(v)
				rowCnt++
			}
		} else {
			if !colVis[index] {
				colVis[index] = true
				res += int64(n-rowCnt) * int64(v)
				colCnt++
			}
		}
	}
	return res
}
