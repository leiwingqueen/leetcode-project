package wc409

// 给你一个 n x n 的二维数组 grid，它包含范围 [0, n2 - 1] 内的不重复元素。
//
//实现 neighborSum 类：
//
//neighborSum(int [][]grid) 初始化对象。
//int adjacentSum(int value) 返回在 grid 中与 value 相邻的元素之和，相邻指的是与 value 在上、左、右或下的元素。
//int diagonalSum(int value) 返回在 grid 中与 value 对角线相邻的元素之和，对角线相邻指的是与 value 在左上、右上、左下或右下的元素。
//
//
//
//
//示例 1：
//
//输入：
//
//["neighborSum", "adjacentSum", "adjacentSum", "diagonalSum", "diagonalSum"]
//
//[[[[0, 1, 2], [3, 4, 5], [6, 7, 8]]], [1], [4], [4], [8]]
//
//输出： [null, 6, 16, 16, 4]
//
//解释：
//
//
//
//1 的相邻元素是 0、2 和 4。
//4 的相邻元素是 1、3、5 和 7。
//4 的对角线相邻元素是 0、2、6 和 8。
//8 的对角线相邻元素是 4。
//示例 2：
//
//输入：
//
//["neighborSum", "adjacentSum", "diagonalSum"]
//
//[[[[1, 2, 0, 3], [4, 7, 15, 6], [8, 9, 10, 11], [12, 13, 14, 5]]], [15], [9]]
//
//输出： [null, 23, 45]
//
//解释：
//
//
//
//15 的相邻元素是 0、10、7 和 6。
//9 的对角线相邻元素是 4、12、14 和 15。
//
//
//提示：
//
//3 <= n == grid.length == grid[0].length <= 10
//0 <= grid[i][j] <= n2 - 1
//所有 grid[i][j] 值均不重复。
//adjacentSum 和 diagonalSum 中的 value 均在范围 [0, n2 - 1] 内。
//最多会调用 adjacentSum 和 diagonalSum 总共 2 * n2 次。

type neighborSum struct {
	grid [][]int
	pos  [][]int
	n    int
}

func Constructor(grid [][]int) neighborSum {
	n := len(grid)
	pos := make([][]int, n*n)
	for i := 0; i < n*n; i++ {
		pos[i] = make([]int, 2)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			v := grid[i][j]
			pos[v][0] = i
			pos[v][1] = j
		}
	}
	return neighborSum{grid: grid, pos: pos, n: n}
}

func (this *neighborSum) AdjacentSum(value int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	p := this.pos[value]
	sum := 0
	for _, dir := range dirs {
		x, y := p[0]+dir[0], p[1]+dir[1]
		if x >= 0 && x < this.n && y >= 0 && y < this.n {
			sum += this.grid[x][y]
		}
	}
	return sum
}

func (this *neighborSum) DiagonalSum(value int) int {
	dirs := [][]int{
		{-1, -1},
		{-1, 1},
		{1, -1},
		{1, 1},
	}
	p := this.pos[value]
	sum := 0
	for _, dir := range dirs {
		x, y := p[0]+dir[0], p[1]+dir[1]
		if x >= 0 && x < this.n && y >= 0 && y < this.n {
			sum += this.grid[x][y]
		}
	}
	return sum
}

/**
 * Your neighborSum object will be instantiated and called as such:
 * obj := Constructor(grid);
 * param_1 := obj.AdjacentSum(value);
 * param_2 := obj.DiagonalSum(value);
 */
