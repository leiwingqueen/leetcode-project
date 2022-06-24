package dp

import "math"

/**

面试题 17.24. 最大子矩阵

给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。

返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。

注意：本题相对书上原题稍作改动

示例：

输入：
[
   [-1,0],
   [0,-1]
]
输出：[0,1,0,1]
解释：输入中标粗的元素即为输出所表示的矩阵
 

说明：

1 <= matrix.length, matrix[0].length <= 200


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/max-submatrix-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
func getMaxMatrix(matrix [][]int) []int {
	row, col := len(matrix), len(matrix[0])
	//前缀和
	prefixSum := make([][]int, row)
	for i := 0; i < row; i++ {
		prefixSum[i] = make([]int, col+1)
		for j := 1; j <= col; j++ {
			prefixSum[i][j] = prefixSum[i][j-1] + matrix[i][j-1]
		}
	}
	max := math.MinInt32
	pos := []int{0, 0, 0, 0}
	for l1 := 0; l1 < col; l1++ {
		for l2 := l1 + 1; l2 <= col; l2++ {
			res := prefixSum[0][l2] - prefixSum[0][l1]
			resPos := []int{0, 0}
			dp := res
			dpStart := 0
			for i := 1; i < row; i++ {
				num := prefixSum[i][l2] - prefixSum[i][l1]
				if dp+num < num {
					dpStart = i
					dp = num
				} else {
					dp += num
				}
				//更新res
				if dp > res {
					res = dp
					resPos[0] = dpStart
					resPos[1] = i
				}
			}
			//更新max
			if res > max {
				max = res
				pos[0] = resPos[0]
				pos[1] = l1
				pos[2] = resPos[1]
				pos[3] = l2 - 1
			}
		}
	}
	return pos
}
