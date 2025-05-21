package wc450

// 给你一个大小为 m x n 的二维字符网格 matrix，用字符串数组表示，其中 matrix[i][j] 表示第 i 行和第 j 列处的单元格。每个单元格可以是以下几种字符之一：
//
//'.' 表示一个空单元格。
//'#' 表示一个障碍物。
//一个大写字母（'A' 到 'Z'）表示一个传送门。
//你从左上角单元格 (0, 0) 出发，目标是到达右下角单元格 (m - 1, n - 1)。你可以从当前位置移动到相邻的单元格（上、下、左、右），移动后的单元格必须在网格边界内且不是障碍物。
//
//如果你踏入一个包含传送门字母的单元格，并且你之前没有使用过该传送门字母，你可以立即传送到网格中另一个具有相同字母的单元格。这次传送不计入移动次数，但每个字母对应的传送门在旅程中 最多 只能使用一次。
//
//返回到达右下角单元格所需的 最少 移动次数。如果无法到达目的地，则返回 -1。
//
//
//
//示例 1：
//
//输入： matrix = ["A..",".A.","..."]
//
//输出： 2
//
//解释：
//
//
//
//在第一次移动之前，从 (0, 0) 传送到 (1, 1)。
//第一次移动，从 (1, 1) 移动到 (1, 2)。
//第二次移动，从 (1, 2) 移动到 (2, 2)。
//示例 2：
//
//输入： matrix = [".#...",".#.#.",".#.#.","...#."]
//
//输出： 13
//
//解释：
//
//
//
//
//
//提示：
//
//1 <= m == matrix.length <= 103
//1 <= n == matrix[i].length <= 103
//matrix[i][j] 是 '#'、'.' 或一个大写英文字母。
//matrix[0][0] 不是障碍物。

func minMoves(matrix []string) int {
	m, n := len(matrix), len(matrix[0])
	// 传送门
	door := make([][][]int, 26)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] >= 'A' && matrix[i][j] <= 'Z' {
				idx := matrix[i][j] - 'A'
				door[idx] = append(door[idx], []int{i, j})
			}
		}
	}
	dis := make([][]int, m)
	for i := 0; i < m; i++ {
		dis[i] = make([]int, n)
		for j := 0; j < n; j++ {
			dis[i][j] = -1
		}
	}
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	var queue [][]int
	queue = append(queue, []int{0, 0})
	if matrix[0][0] == '.' {
		dis[0][0] = 0
	} else {
		idx := matrix[0][0] - 'A'
		// 所有传送门都赋值
		for _, item := range door[idx] {
			dis[item[0]][item[1]] = 0
			queue = append(queue, []int{item[0], item[1]})
		}
	}
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			x, y := queue[i][0], queue[i][1]
			for _, dir := range dirs {
				nx, ny := x+dir[0], y+dir[1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && dis[nx][ny] < 0 && matrix[nx][ny] != '#' {
					if matrix[nx][ny] == '.' {
						dis[nx][ny] = depth + 1
						queue = append(queue, []int{nx, ny})
					} else {
						idx := matrix[nx][ny] - 'A'
						// 所有传送门都赋值
						for _, item := range door[idx] {
							if dis[item[0]][item[1]] < 0 {
								dis[item[0]][item[1]] = depth + 1
								queue = append(queue, []int{item[0], item[1]})
							}
						}
					}
				}
			}
		}
		queue = queue[size:]
		depth++
	}
	return dis[m-1][n-1]
}
