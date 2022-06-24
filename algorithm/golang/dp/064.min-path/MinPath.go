package _64_min_path

/**
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

/**
使用dp的解法解题
假设f(i,j)为从i,j位置出发到grid[m-1][n-1]的最短路径
f(i,j)=min{f(i+1,j),f(i,j+1)}+grid[i][j]

初始化：
i=m-1:
f(m-1,j)=f(m-1,j+1)+grid[m-1][j]

j=n-1:
f(i,n-1)=f(i+1,n-1)+grid[i][n-1]
*/
func minPathSum(grid [][]int) int {
	if len(grid) == 0 {
		return 0
	}
	row, col := len(grid), len(grid[0])
	result := make([][]int, row)
	for i := 0; i < row; i++ {
		result[i] = make([]int, col)
	}
	//var result [10000][10000]int
	//初始化
	result[row-1][col-1] = grid[row-1][col-1]
	for i := row - 2; i >= 0; i-- {
		result[i][col-1] = result[i+1][col-1] + grid[i][col-1]
	}
	for i := col - 2; i >= 0; i-- {
		result[row-1][i] = result[row-1][i+1] + grid[row-1][i]
	}
	//dp过程
	for i := row - 2; i >= 0; i-- {
		for j := col - 2; j >= 0; j-- {
			result[i][j] = min(result[i+1][j], result[i][j+1]) + grid[i][j]
		}
	}
	return result[0][0]
}

func min(a int, b int) int {
	if a <= b {
		return a
	} else {
		return b
	}
}
