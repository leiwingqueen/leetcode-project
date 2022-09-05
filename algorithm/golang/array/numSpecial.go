package array

//给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
//
//特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
//
//
//
//示例 1：
//
//输入：mat = [[1,0,0],
//            [0,0,1],
//            [1,0,0]]
//输出：1
//解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
//示例 2：
//
//输入：mat = [[1,0,0],
//            [0,1,0],
//            [0,0,1]]
//输出：3
//解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
//示例 3：
//
//输入：mat = [[0,0,0,1],
//            [1,0,0,0],
//            [0,1,1,0],
//            [0,0,0,0]]
//输出：2
//示例 4：
//
//输入：mat = [[0,0,0,0,0],
//            [1,0,0,0,0],
//            [0,1,0,0,0],
//            [0,0,1,0,0],
//            [0,0,0,1,1]]
//输出：3
//
//
//提示：
//
//rows == mat.length
//cols == mat[i].length
//1 <= rows, cols <= 100
//mat[i][j] 是 0 或 1
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/special-positions-in-a-binary-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func numSpecial(mat [][]int) int {
	m := len(mat)
	n := len(mat[0])
	cnt := 0
	for i := 0; i < m; i++ {
		sum := 0
		id := 0
		for j := 0; j < n; j++ {
			if mat[i][j] == 1 {
				sum += mat[i][j]
				id = j
			}
		}
		if sum == 1 {
			sum := 0
			for k := 0; k < m; k++ {
				sum += mat[k][id]
			}
			if sum == 1 {
				cnt++
			}
		}
	}
	return cnt
}

// 优化解法
func numSpecial2(mat [][]int) int {
	m := len(mat)
	n := len(mat[0])
	rows := make([]int, m)
	cols := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			rows[i] += mat[i][j]
			cols[j] += mat[i][j]
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1 {
				res++
			}
		}
	}
	return res
}
