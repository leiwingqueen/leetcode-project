package array

//给定 m x n 矩阵 matrix 。
//
// 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
//
// 返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：matrix = [[0,1],[1,1]]
//输出：1
//解释：不进行翻转，有 1 行所有值都相等。
//
//
// 示例 2：
//
//
//输入：matrix = [[0,1],[1,0]]
//输出：2
//解释：翻转第一列的值之后，这两行都由相等的值组成。
//
//
// 示例 3：
//
//
//输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
//输出：2
//解释：翻转前两列的值之后，后两行由相等的值组成。
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// matrix[i][j] == 0 或 1
//
//
// Related Topics 数组 哈希表 矩阵 👍 96 👎 0

// 暴力，勉强通过。时间复杂度O(m^2*n)
func maxEqualRowsAfterFlips(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	cal := func(flip []int) int {
		cnt := 0
		for i := 0; i < m; i++ {
			flag := true
			num := matrix[i][0] ^ flip[0]
			for j := 1; j < n; j++ {
				if matrix[i][j]^flip[j] != num {
					flag = false
					break
				}
			}
			if flag {
				cnt++
			}
		}
		return cnt
	}
	// 让具体的某一行变成相同的方案
	res := 0
	for i := 0; i < m; i++ {
		flip1 := make([]int, n)
		for j := 0; j < n; j++ {
			flip1[j] = matrix[i][j] ^ 1
		}
		c1 := cal(flip1)
		if c1 > res {
			res = c1
		}
		flip2 := make([]int, n)
		for j := 0; j < n; j++ {
			flip2[j] = matrix[i][j]
		}
		c2 := cal(flip2)
		if c2 > res {
			res = c2
		}
	}
	return res
}
