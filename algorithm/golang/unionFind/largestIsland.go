package unionFind

// 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
//
//返回执行此操作后，grid 中最大的岛屿面积是多少？
//
//岛屿 由一组上、下、左、右四个方向相连的 1 形成。
//
//
//
//示例 1:
//
//输入: grid = [[1, 0], [0, 1]]
//输出: 3
//解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
//示例 2:
//
//输入: grid = [[1, 1], [1, 0]]
//输出: 4
//解释: 将一格0变成1，岛屿的面积扩大为 4。
//示例 3:
//
//输入: grid = [[1, 1], [1, 1]]
//输出: 4
//解释: 没有0可以让我们变成1，面积依然为 4。
//
//
//提示：
//
//n == grid.length
//n == grid[i].length
//1 <= n <= 500
//grid[i][j] 为 0 或 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/making-a-large-island
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

var DIRS = [][]int{
	{0, -1},
	{0, 1},
	{-1, 0},
	{1, 0},
}

func largestIsland(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	uf := ConstructIsland(grid)
	for i, row := range grid {
		for j, d := range row {
			if d == 1 {
				for _, dir := range DIRS {
					x := i + dir[0]
					y := j + dir[1]
					if x >= 0 && x < uf.m && y >= 0 && y < uf.n && uf.grid[x][y] == 1 {
						uf.union(x*n+y, i*n+j)
					}
				}
			}
		}
	}
	max := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if uf.count[i*n+j] > max {
				max = uf.count[i*n+j]
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				merge := uf.tryMerge([]int{i, j})
				if merge > max {
					max = merge
				}
			}
		}
	}
	return max
}

type IslandUnionFind struct {
	parent []int
	// 每个连通块的大小
	count []int
	grid  [][]int
	m     int
	n     int
	max   int
}

func ConstructIsland(grid [][]int) *IslandUnionFind {
	m := len(grid)
	n := len(grid[0])
	arr := make([]int, m*n)
	count := make([]int, m*n)
	max := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			arr[i*n+j] = i*n + j
			if grid[i][j] == 1 {
				count[i*n+j] = 1
				max = 1
			}
		}
	}
	return &IslandUnionFind{arr, count, grid, m, n, max}
}

func (uf *IslandUnionFind) find(x int) int {
	for uf.parent[x] != x {
		//路径压缩
		uf.parent[x] = uf.parent[uf.parent[x]]
		x = uf.parent[x]
	}
	return uf.parent[x]
}

func (uf *IslandUnionFind) union(x int, y int) {
	rootX := uf.find(x)
	rootY := uf.find(y)
	if rootX != rootY {
		cnt := uf.count[rootX]
		uf.parent[rootX] = rootY
		uf.count[rootY] += cnt
	}
}

func (uf *IslandUnionFind) tryMerge(pos []int) int {
	set := make(map[int]bool)
	for _, dir := range DIRS {
		x := pos[0] + dir[0]
		y := pos[1] + dir[1]
		if x >= 0 && x < uf.m && y >= 0 && y < uf.n && uf.grid[x][y] == 1 {
			root := uf.find(x*uf.n + y)
			set[root] = true
		}
	}
	res := 1
	for root := range set {
		res += uf.count[root]
	}
	return res
}
