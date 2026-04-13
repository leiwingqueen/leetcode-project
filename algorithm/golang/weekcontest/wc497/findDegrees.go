package wc497

func findDegrees(matrix [][]int) []int {
	n := len(matrix)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if matrix[i][j] == 1 {
				res[i]++
				res[j]++
			}
		}
	}
	return res
}
