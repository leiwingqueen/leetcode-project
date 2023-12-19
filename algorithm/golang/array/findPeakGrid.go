package array

// 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
//
//给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
//
//你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
//
//要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
//
//
//
//
//
//示例 1:
//
//
//
//输入: mat = [[1,4],[3,2]]
//输出: [0,1]
//解释: 3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
//示例 2:
//
//
//
//输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
//输出: [1,1]
//解释: 30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
//
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 500
//1 <= mat[i][j] <= 105
//任意两个相邻元素均不相等.

// 暴力解法，OK，但是效率不高
func findPeakGrid(mat [][]int) []int {
	m, n := len(mat), len(mat[0])
	dirs := [][]int{
		{-1, -1},
		{-1, 0},
		{-1, 1},
		{0, -1},
		{0, 1},
		{1, -1},
		{1, 0},
		{1, 1},
	}
	check := func(x, y int) bool {
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && mat[nx][ny] >= mat[x][y] {
				return false
			}
		}
		return true
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if check(i, j) {
				return []int{i, j}
			}
		}
	}
	return []int{}
}
