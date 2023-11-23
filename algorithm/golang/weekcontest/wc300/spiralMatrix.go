package wc300

/**
 * Definition for singly-linked list.
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

func spiralMatrix(m int, n int, head *ListNode) [][]int {
	matrix := make([][]int, m)
	for i := 0; i < m; i++ {
		matrix[i] = make([]int, n)
		for j := 0; j < n; j++ {
			matrix[i][j] = -1
		}
	}
	dirs := [][]int{
		{0, 1},
		{1, 0},
		{0, -1},
		{-1, 0},
	}
	d := 0
	x, y := 0, 0
	for node := head; node != nil; node = node.Next {
		matrix[x][y] = node.Val
		// 判断是否需要转向
		nx, ny := x+dirs[d][0], y+dirs[d][1]
		if nx < 0 || nx >= m && ny < 0 || ny >= n {
			d = (d + 1) % 4
			nx, ny = x+dirs[d][0], y+dirs[d][1]
		}
		x, y = nx, ny
	}
	return matrix
}
