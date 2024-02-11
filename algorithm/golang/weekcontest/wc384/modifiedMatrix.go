package wc384

func modifiedMatrix(matrix [][]int) [][]int {
	m, n := len(matrix), len(matrix[0])
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	mxArr := make([]int, n)
	for i := 0; i < n; i++ {
		mx := -1
		for j := 0; j < m; j++ {
			mx = max(mx, matrix[j][i])
		}
		mxArr[i] = mx
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] == -1 {
				matrix[i][j] = mxArr[j]
			}
		}
	}
	return matrix
}
