package unionFind

import "leetcode-go/util"

// 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
//
//岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
//
//你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
//
//返回必须翻转的 0 的最小数目。
//
//
//
//示例 1：
//
//输入：grid = [[0,1],[1,0]]
//输出：1
//示例 2：
//
//输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
//输出：2
//示例 3：
//
//输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
//输出：1
//
//
//提示：
//
//n == grid.length == grid[i].length
//2 <= n <= 100
//grid[i][j] 为 0 或 1
//grid 中恰有两个岛
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-bridge
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func shortestBridge(grid [][]int) int {
	n := len(grid)
	var DIRS = [][]int{
		{0, -1},
		{0, 1},
		{-1, 0},
		{1, 0},
	}
	lands := make([][]int, 0)
	var dfs func(pos []int, color int)
	dfs = func(pos []int, color int) {
		x := pos[0]
		y := pos[1]
		if grid[x][y] == 1 {
			grid[x][y] = color
			for _, dir := range DIRS {
				nx := x + dir[0]
				ny := y + dir[1]
				if nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1 {
					dfs([]int{nx, ny}, color)
				}
			}
		}
	}
	color := 2
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				lands = append(lands, []int{i, j})
				dfs([]int{i, j}, color)
				color++
			}
		}
	}
	// 比较最短距离
	res := 2 * n
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 2 {
				for k := 0; k < n; k++ {
					for l := 0; l < n; l++ {
						if grid[k][l] == 3 {
							dis := util.Abs(i-k) - 1 + util.Abs(j-l) - 1
							if dis < res {
								res = dis
							}
						}
					}
				}
			}
		}
	}
	return res
}
