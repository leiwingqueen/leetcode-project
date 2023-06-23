package array

//在 `n*m` 大小的棋盘中，有黑白两种棋子，黑棋记作字母 `"X"`, 白棋记作字母 `"O"`，空余位置记作 `"."`。当落下的棋子与其他相同颜色的棋
//子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
//
//![1.gif](https://pic.leetcode-cn.com/1630396029-eTgzpN-6da662e67368466a96d203
//f67bb6e793.gif){:height=170px}![2.gif](https://pic.leetcode-cn.com/1630396240-
//nMvdcc-8e4261afe9f60e05a4f740694b439b6b.gif){:height=170px}![3.gif](https://pic.
//leetcode-cn.com/1630396291-kEtzLL-6fcb682daeecb5c3f56eb88b23c81d33.gif){:height=170
//px}
//
//「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 `chessboard`。若下一步可放置一枚黑棋，请问选手最多能翻转
//多少枚白棋。
//
//**注意：**
//- 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 **继续** 翻转白棋
//- 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
//
//**示例 1：**
//
//> 输入：`chessboard = ["....X.","....X.","XOOO..","......","......"]`
//>
//> 输出：`3`
//>
//> 解释：
//> 可以选择下在 `[2,4]` 处，能够翻转白方三枚棋子。
//
//**示例 2：**
//
//> 输入：`chessboard = [".X.",".O.","XO."]`
//>
//> 输出：`2`
//>
//> 解释：
//> 可以选择下在 `[2,2]` 处，能够翻转白方两枚棋子。
//> ![2126c1d21b1b9a9924c639d449cc6e65.gif](https://pic.leetcode-cn.com/16266832
//55-OBtBud-2126c1d21b1b9a9924c639d449cc6e65.gif)
//
//**示例 3：**
//
//> 输入：`chessboard = [".......",".......",".......","X......",".O.....","..O....
//","....OOX"]`
//>
//> 输出：`4`
//>
//> 解释：
//> 可以选择下在 `[6,3]` 处，能够翻转白方四枚棋子。
//> ![803f2f04098b6174397d6c696f54d709.gif](https://pic.leetcode-cn.com/16303937
//70-Puyked-803f2f04098b6174397d6c696f54d709.gif)
//
//**提示：**
//- `1 <= chessboard.length, chessboard[i].length <= 8`
//- `chessboard[i]` 仅包含 `"."、"O"` 和 `"X"`
//
// Related Topics 广度优先搜索 数组 矩阵 👍 44 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
func flipChess(chessboard []string) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
		{-1, -1},
		{-1, 1},
		{1, -1},
		{1, 1},
	}
	m, n := len(chessboard), len(chessboard[0])
	matrix := make([][]int, m)
	for i := 0; i < m; i++ {
		matrix[i] = make([]int, n)
		for j := 0; j < n; j++ {
			if chessboard[i][j] == '.' {
				matrix[i][j] = 0
			} else if chessboard[i][j] == 'O' {
				matrix[i][j] = 1
			} else {
				matrix[i][j] = 2
			}
		}
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		res := 0
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			cnt := 0
			for nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 1 {
				nx += dir[0]
				ny += dir[1]
				cnt++
			}
			if cnt > 0 && nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 2 {
				// 更新颜色
				for i := 1; i <= cnt; i++ {
					matrix[x+i*dir[0]][y+i*dir[1]] = 2
				}
				res += cnt
				for i := 1; i <= cnt; i++ {
					res += dfs(x+i*dir[0], y+i*dir[1])
				}
				for i := 1; i <= cnt; i++ {
					matrix[x+i*dir[0]][y+i*dir[1]] = 1
				}
			}
		}
		return res
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if chessboard[i][j] == '.' {
				sub := dfs(i, j)
				if sub > res {
					res = sub
				}
			}
		}
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

// bfs，总算通过了
func flipChess2(chessboard []string) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
		{-1, -1},
		{-1, 1},
		{1, -1},
		{1, 1},
	}
	m, n := len(chessboard), len(chessboard[0])
	makeMatrix := func() [][]int {
		matrix := make([][]int, m)
		for i := 0; i < m; i++ {
			matrix[i] = make([]int, n)
			for j := 0; j < n; j++ {
				if chessboard[i][j] == '.' {
					matrix[i][j] = 0
				} else if chessboard[i][j] == 'O' {
					matrix[i][j] = 1
				} else {
					matrix[i][j] = 2
				}
			}
		}
		return matrix
	}
	var bfs func(matrix [][]int, x, y int) int
	bfs = func(matrix [][]int, x, y int) int {
		res := 0
		var queue [][]int
		queue = append(queue, []int{x, y})
		for len(queue) > 0 {
			node := queue[0]
			queue = queue[1:]
			for _, dir := range dirs {
				nx, ny := node[0]+dir[0], node[1]+dir[1]
				cnt := 0
				for nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 1 {
					nx += dir[0]
					ny += dir[1]
					cnt++
				}
				if cnt > 0 && nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 2 {
					// 更新颜色
					for i := 1; i <= cnt; i++ {
						matrix[node[0]+i*dir[0]][node[1]+i*dir[1]] = 2
					}
					res += cnt
					for i := 1; i <= cnt; i++ {
						queue = append(queue, []int{node[0] + i*dir[0], node[1] + i*dir[1]})
					}
				}
			}
		}
		return res
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if chessboard[i][j] == '.' {
				matrix := makeMatrix()
				sub := bfs(matrix, i, j)
				if sub > res {
					res = sub
				}
			}
		}
	}
	return res
}
