package array

// 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
//
//
//
//示例 1：
//
//输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
//输出：9
//示例 2：
//
//输入：grid = [[1,1,0,0]]
//输出：1
//
//
//提示：
//
//1 <= grid.length <= 100
//1 <= grid[0].length <= 100
//grid[i][j] 为 0 或 1
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-1-bordered-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func largest1BorderedSquare(grid [][]int) int {
	res := 0
	m := len(grid)
	n := len(grid[0])
	mk := m
	if n < m {
		mk = n
	}
	row := make([][]int, m)
	for i := 0; i < m; i++ {
		row[i] = make([]int, n+1)
		for j := 0; j < n; j++ {
			row[i][j+1] = row[i][j] + grid[i][j]
		}
	}
	col := make([][]int, n)
	for i := 0; i < n; i++ {
		col[i] = make([]int, m+1)
		for j := 0; j < m; j++ {
			col[i][j+1] = col[i][j] + grid[j][i]
		}
	}
	check := func(x int, y int, k int) bool {
		r1 := row[x][y+k] - row[x][y]
		if r1 != k {
			return false
		}
		r2 := row[x+k-1][y+k] - row[x+k-1][y]
		if r2 != k {
			return false
		}
		c1 := col[y][x+k] - col[y][x]
		if c1 != k {
			return false
		}
		c2 := col[y+k-1][x+k] - col[y+k-1][x]
		if c2 != k {
			return false
		}
		return true
	}
	check2 := func(k int) bool {
		for i := 0; i <= m-k; i++ {
			for j := 0; j <= n-k; j++ {
				if check(i, j, k) {
					return true
				}
			}
		}
		return false
	}
	for k := 1; k <= mk; k++ {
		if check2(k) {
			res = k * k
		}
	}
	return res
}
