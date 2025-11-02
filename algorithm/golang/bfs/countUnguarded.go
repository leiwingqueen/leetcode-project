package bfs

// 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。同时给你两个二维整数数组 guards 和 walls ，其中 guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，分别表示第 i 个警卫和第 j 座墙所在的位置。
//
//一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，除非他们被一座墙或者另外一个警卫 挡住 了视线。如果一个格子能被 至少 一个警卫看到，那么我们说这个格子被 保卫 了。
//
//请你返回空格子中，有多少个格子是 没被保卫 的。
//
//
//
//示例 1：
//
//
//
//输入：m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
//输出：7
//解释：上图中，被保卫和没有被保卫的格子分别用红色和绿色表示。
//总共有 7 个没有被保卫的格子，所以我们返回 7 。
//示例 2：
//
//
//
//输入：m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
//输出：4
//解释：上图中，没有被保卫的格子用绿色表示。
//总共有 4 个没有被保卫的格子，所以我们返回 4 。
//
//
//提示：
//
//1 <= m, n <= 105
//2 <= m * n <= 105
//1 <= guards.length, walls.length <= 5 * 104
//2 <= guards.length + walls.length <= m * n
//guards[i].length == walls[j].length == 2
//0 <= rowi, rowj < m
//0 <= coli, colj < n
//guards 和 walls 中所有位置 互不相同 。

func countUnguarded(m int, n int, guards [][]int, walls [][]int) int {
	seen := make([][]bool, m)
	for i := 0; i < m; i++ {
		seen[i] = make([]bool, n)
	}
	wallMap := make(map[int]map[int]struct{})
	for _, w := range walls {
		x, y := w[0], w[1]
		if _, ok := wallMap[x]; !ok {
			wallMap[x] = make(map[int]struct{})
		}
		wallMap[x][y] = struct{}{}
		seen[x][y] = true
	}
	isWall := func(x, y int) bool {
		if _, ok := wallMap[x]; !ok {
			return false
		}
		if _, ok := wallMap[x][y]; ok {
			return true
		}
		return false
	}
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	bfs := func(x, y int) {
		var queue [][]int
		queue = append(queue, []int{x, y})
		visit := make([][]bool, m)
		for i := 0; i < m; i++ {
			visit[i] = make([]bool, n)
		}
		visit[x][y] = true
		seen[x][y] = true
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				x1, y1 := queue[i][0], queue[i][1]
				for _, dir := range dirs {
					x2, y2 := x1+dir[0], y1+dir[1]
					if x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && !isWall(x2, y2) && !visit[x2][y2] {
						visit[x2][y2] = true
						seen[x2][y2] = true
						queue = append(queue, []int{x2, y2})
					}
				}
			}
			queue = queue[size:]
		}
	}
	for _, g := range guards {
		bfs(g[0], g[1])
	}
	cnt := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if !seen[i][j] {
				cnt++
			}
		}
	}
	return cnt
}
