package array

// You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].
//
//Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].
//
//Return the smallest index i at which either a row or a column will be completely painted in mat.
//
//
//
//Example 1:
//
//image explanation for example 1
//Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
//Output: 2
//Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].

func firstCompleteIndex(arr []int, mat [][]int) int {
	m, n := len(mat), len(mat[0])
	rows, cols := make([]int, m), make([]int, n)
	pos := make([][]int, m*n+1)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			pos[mat[i][j]] = []int{i, j}
		}
	}
	for i, v := range arr {
		x, y := pos[v][0], pos[v][1]
		rows[x]++
		cols[y]++
		if rows[x] == n || cols[y] == m {
			return i
		}
	}
	return m*n - 1
}
