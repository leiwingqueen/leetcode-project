package array

import "math"

//给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
//
//幸运数是指矩阵中满足同时下列两个条件的元素：
//
//在同一行的所有元素中最小
//在同一列的所有元素中最大
// 
//
//示例 1：
//
//输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
//输出：[15]
//解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
//示例 2：
//
//输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
//输出：[12]
//解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
//示例 3：
//
//输入：matrix = [[7,8],[1,2]]
//输出：[7]
// 
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= n, m <= 50
//1 <= matrix[i][j] <= 10^5
//矩阵中的所有元素都是不同的
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
模拟。时间复杂度O(m*n)
*/
func luckyNumbers(matrix [][]int) []int {
	res := make([]int, 0)
	m := len(matrix)
	n := len(matrix[0])
	for i := 0; i < m; i++ {
		//每行最小的下标
		min := math.MaxInt32
		idx := -1
		for j := 0; j < n; j++ {
			if matrix[i][j] < min {
				min = matrix[i][j]
				idx = j
			}
		}
		//判断这个下标是否这一列最大的数字
		flag := true
		for j := 0; j < m; j++ {
			if j != i {
				if matrix[j][idx] > matrix[i][idx] {
					flag = false
					break
				}
			}
		}
		if flag {
			res = append(res, matrix[i][idx])
		}
	}
	return res
}

//分别求出每一行最小的下标和每一列最大的下标
func luckyNumbers2(matrix [][]int) []int {
	m := len(matrix)
	n := len(matrix[0])
	row := make([]int, m)
	col := make([]int, n)
	for i := 0; i < m; i++ {
		min := math.MaxInt32
		idx := -1
		for j := 0; j < n; j++ {
			if matrix[i][j] < min {
				min = matrix[i][j]
				idx = j
			}
		}
		row[i] = idx
	}
	for i := 0; i < n; i++ {
		max := 0
		idx := -1
		for j := 0; j < m; j++ {
			if matrix[j][i] > max {
				max = matrix[j][i]
				idx = j
			}
		}
		col[i] = idx
	}
	//比较行列分别的下标是否一致
	res := make([]int, 0)
	for i := 0; i < m; i++ {
		if col[row[i]] == i {
			res = append(res, matrix[i][row[i]])
		}
	}
	return res
}
