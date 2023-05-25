package backtrace

import "fmt"

// 「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
//
//游戏地图用大小为 m x n 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
//
//现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
//
//玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
//地板用字符 '.' 表示，意味着可以自由行走。
//墙用字符 '#' 表示，意味着障碍物，不能通行。
//箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
//玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
//玩家无法越过箱子。
//返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。
//
//
//
//示例 1：
//
//
//
//输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//             ["#",".",".","B",".","#"],
//             ["#",".","#","#",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：3
//解释：我们只需要返回推箱子的次数。
//示例 2：
//
//输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//             ["#",".",".","B",".","#"],
//             ["#","#","#","#",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：-1
//示例 3：
//
//输入：grid = [["#","#","#","#","#","#"],
//             ["#","T",".",".","#","#"],
//             ["#",".","#","B",".","#"],
//             ["#",".",".",".",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：5
//解释：向下、向左、向左、向上再向上。
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 20
//grid 仅包含字符 '.', '#',  'S' , 'T', 以及 'B'。
//grid 中 'S', 'B' 和 'T' 各只能出现一个。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func minPushBox(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	// 由于墙的位置是不动的，因此我们可以单独把墙的位置拿出来
	blocks := make([][]bool, m)
	for i := 0; i < m; i++ {
		blocks[i] = make([]bool, n)
	}
	source, target, box := []int{0, 0}, []int{0, 0}, []int{0, 0}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '#' {
				blocks[i][j] = true
			} else if grid[i][j] == 'S' {
				source[0], source[1] = i, j
			} else if grid[i][j] == 'B' {
				box[0], box[1] = i, j
			} else if grid[i][j] == 'T' {
				target[0], target[1] = i, j
			}
		}
	}
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	// 为了避免在重复的路径上浪费时间，增加一个map来保存已经存在的场景
	keyMap := func(source []int, box []int) string {
		return fmt.Sprintf("%d#%d#%d#%d", source[0], source[1], box[0], box[1])
	}
	cache := make(map[string]int)
	var dfs func(source []int, box []int, visit [][]bool) int
	dfs = func(source []int, box []int, visit [][]bool) int {
		fmt.Println(fmt.Sprintf("source:%v,box:%v", source, box))
		if box[0] == target[0] && box[1] == target[1] {
			return 0
		}
		key := keyMap(source, box)
		if v, exist := cache[key]; exist {
			return v
		}
		visit[source[0]][source[1]] = true
		res := -1
		for _, dir := range dirs {
			x, y := source[0]+dir[0], source[1]+dir[1]
			if x >= 0 && x < m && y >= 0 && y < n && !blocks[x][y] && !visit[x][y] {
				// 如果对应的位置是箱子，则箱子也按对应方向移动一格
				if box[0] == x && box[1] == y {
					bx, by := box[0]+dir[0], box[1]+dir[1]
					if bx >= 0 && bx < m && by >= 0 && by < n && !blocks[bx][by] {
						// 一旦移动了箱子，允许走回头路
						for i := 0; i < m; i++ {
							for j := 0; j < n; j++ {
								visit[i][j] = false
							}
						}
						sub := dfs([]int{x, y}, []int{bx, by}, visit)
						if sub >= 0 && (res < 0 || sub > res) {
							res = sub + 1
						}
					}
				} else {
					sub := dfs([]int{x, y}, box, visit)
					if sub >= 0 && (res < 0 || sub > res) {
						res = sub
					}
				}
			}
		}
		cache[key] = res
		return res
	}
	visit := make([][]bool, m)
	for i := 0; i < m; i++ {
		visit[i] = make([]bool, n)
	}
	return dfs(source, box, visit)
}
