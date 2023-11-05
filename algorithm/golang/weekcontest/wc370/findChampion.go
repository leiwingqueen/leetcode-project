package wc370

// 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。
//
//给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
//
//在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
//
//返回这场比赛中将会成为冠军的队伍。
//
//
//
//示例 1：
//
//输入：grid = [[0,1],[0,0]]
//输出：0
//解释：比赛中有两支队伍。
//grid[0][1] == 1 表示 0 队比 1 队强。所以 0 队是冠军。
//示例 2：
//
//输入：grid = [[0,0,1],[1,0,1],[0,0,0]]
//输出：1
//解释：比赛中有三支队伍。
//grid[1][0] == 1 表示 1 队比 0 队强。
//grid[1][2] == 1 表示 1 队比 2 队强。
//所以 1 队是冠军。
//
//
//提示：
//
//n == grid.length
//n == grid[i].length
//2 <= n <= 100
//grid[i][j] 的值为 0 或 1
//对于满足 i != j 的所有 i, j ，grid[i][j] != grid[j][i] 均成立
//生成的输出满足：如果 a 队比 b 队强，b 队比 c 队强，那么 a 队比 c 队强

func findChampion(grid [][]int) int {
	// 计算入度为0
	n := len(grid)
	degree := make([]int, n)
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if grid[i][j] > 0 {
				degree[j]++
			} else {
				degree[i]++
			}
		}
	}
	for i, d := range degree {
		if d == 0 {
			return i
		}
	}
	return -1
}

func findChampion2(n int, edges [][]int) int {
	degree := make([]int, n)
	for _, edge := range edges {
		_, y := edge[0], edge[1]
		degree[y]++
	}
	cnt := 0
	res := -1
	for i, d := range degree {
		if d == 0 {
			res = i
			cnt++
		}
	}
	if cnt == 1 {
		return res
	} else {
		return -1
	}
}
