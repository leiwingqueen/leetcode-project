package bwc97

// 给你一个下标从 0 开始的 m x n 二进制 矩阵 grid 。你可以从一个格子 (row, col) 移动到格子 (row + 1, col) 或者 (row, col + 1) ，前提是前往的格子值为 1 。如果从 (0, 0) 到 (m - 1, n - 1) 没有任何路径，我们称该矩阵是 不连通 的。
//
//你可以翻转 最多一个 格子的值（也可以不翻转）。你 不能翻转 格子 (0, 0) 和 (m - 1, n - 1) 。
//
//如果可以使矩阵不连通，请你返回 true ，否则返回 false 。
//
//注意 ，翻转一个格子的值，可以使它的值从 0 变 1 ，或从 1 变 0 。
//
//
//
//示例 1：
//
//
//
//输入：grid = [[1,1,1],[1,0,0],[1,1,1]]
//输出：true
//解释：按照上图所示我们翻转蓝色格子里的值，翻转后从 (0, 0) 到 (2, 2) 没有路径。
//示例 2：
//
//
//
//输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
//输出：false
//解释：无法翻转至多一个格子，使 (0, 0) 到 (2, 2) 没有路径。
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 1000
//1 <= m * n <= 105
//grid[0][0] == grid[m - 1][n - 1] == 1

// TODO:还是不通过
func isPossibleToCutPath(grid [][]int) bool {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	m := len(grid)
	n := len(grid[0])
	if m == 1 && n == 1 {
		return false
	}
	uf := Construct(m * n)
	cnt := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 && j == 0 || i == m-1 && j == n-1 {
				continue
			}
			if grid[i][j] == 0 {
				continue
			}
			start := uf.find(0)
			end := uf.find(m*n - 1)
			m1 := false
			m2 := false
			zones := make(map[int]struct{})
			for _, dir := range dirs {
				x := i + dir[0]
				y := j + dir[1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
					z := uf.find(x*n + y)
					zones[z] = struct{}{}
					if z == start {
						m1 = true
					}
					if z == end {
						m2 = true
					}
				}
			}
			if !m1 || !m2 {
				cnt++
				for zone := range zones {
					uf.union(zone, i*n+j)
				}
			}
		}
	}
	if cnt == 0 {
		return false
	}
	cnt += 2
	c := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				c++
			}
		}
	}
	return cnt >= c-1
}

type UnionFind struct {
	parent []int
}

func Construct(n int) *UnionFind {
	grid := make([]int, n)
	for i := 0; i < n; i++ {
		grid[i] = i
	}
	return &UnionFind{grid}
}

func (uf *UnionFind) find(x int) int {
	for uf.parent[x] != uf.parent[uf.parent[x]] {
		//路径压缩
		uf.parent[x] = uf.parent[uf.parent[x]]
	}
	return uf.parent[x]
}

func (uf *UnionFind) union(x int, y int) {
	rootX := uf.find(x)
	rootY := uf.find(y)
	if rootX != rootY {
		uf.parent[rootX] = rootY
	}
}
