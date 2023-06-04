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

func matrixSumQueries2(n int, queries [][]int) int64 {
	// 记录每一行对应的行和列的最后修改
	rows, cols := make([]int, n), make([]int, n)
	for _, query := range queries {
		t, index, v := query[0], query[1], query[2]
		if t == 0 {
			rows[index] = v
		} else {
			cols[index] = v
		}
	}
	return 0
}
