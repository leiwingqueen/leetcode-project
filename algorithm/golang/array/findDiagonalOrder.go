package array

//498. 对角线遍历
//给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
//
//
//
//示例 1：
//
//
//输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,4,7,5,3,6,8,9]
//示例 2：
//
//输入：mat = [[1,2],[3,4]]
//输出：[1,2,3,4]
//
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 104
//1 <= m * n <= 104
//-105 <= mat[i][j] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/diagonal-traverse
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findDiagonalOrder(mat [][]int) []int {
	m := len(mat)
	n := len(mat[0])
	res := make([]int, m*n)
	x := 0
	y := 0
	dir := 0
	idx := 0
	for idx < m*n {
		if dir == 0 {
			for x >= 0 && y < n {
				res[idx] = mat[x][y]
				x--
				y++
				idx++
			}
			if y >= n {
				x += 2
				y--
			} else {
				x++
			}
			dir = 1
		} else {
			for x < m && y >= 0 {
				res[idx] = mat[x][y]
				x++
				y--
				idx++
			}
			if x >= m {
				y += 2
				x--
			} else {
				y++
			}
			dir = 0
		}
	}
	return res
}
