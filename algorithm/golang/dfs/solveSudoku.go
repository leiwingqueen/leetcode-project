package dfs

//编写一个程序，通过填充空格来解决数独问题。
//
// 数独的解法需 遵循如下规则：
//
//
// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
//
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
//
//
//
//
//
//
//
// 提示：
//
//
// board.length == 9
// board[i].length == 9
// board[i][j] 是一位数字或者 '.'
// 题目数据 保证 输入数独仅有一个解
//
//
// Related Topics 数组 哈希表 回溯 矩阵 👍 1855 👎 0

// leetcode submit region begin(Prohibit modification and deletion)
func solveSudoku(board [][]byte) {
	n := 9
	tmp := make([][]byte, n)
	for i := 0; i < n; i++ {
		tmp[i] = make([]byte, n)
		for j := 0; j < n; j++ {
			tmp[i][j] = board[i][j]
		}
	}
	var dfs func(x, y int, rows []int, cols []int, grids []int) bool
	dfs = func(x, y int, rows []int, cols []int, grids []int) bool {
		if y == n {
			y = 0
			x++
		}
		if x >= n {
			for i := 0; i < n; i++ {
				copy(board[i], tmp[i])
			}
			return true
		}
		if tmp[x][y] != '.' {
			i := tmp[x][y] - '1'
			idx := x/3*3 + y/3
			if rows[x]&(1<<i) != 0 || cols[y]&(1<<i) != 0 || grids[idx]&(1<<i) != 0 {
				return false
			}
			rows[x] |= 1 << i
			cols[y] |= 1 << i
			grids[idx] |= 1 << i
			return dfs(x, y+1, rows, cols, grids)
		} else {
			for i := 0; i < n; i++ {
				// 每个数字都尝试一次
				idx := x/3*3 + y/3
				if rows[x]&(1<<i) == 0 && cols[y]&(1<<i) == 0 && grids[idx]&(1<<i) == 0 {
					rows[x] |= 1 << i
					cols[y] |= 1 << i
					grids[idx] |= 1 << i
					tmp[x][y] = byte(i) + '1'
					b := dfs(x, y+1, rows, cols, grids)
					if b {
						return true
					}
					tmp[x][y] = '.'
					rows[x] ^= 1 << i
					cols[y] ^= 1 << i
					grids[idx] ^= 1 << i
				}
			}
			return false
		}
	}
	dfs(0, 0, make([]int, n), make([]int, n), make([]int, n))
}

//leetcode submit region end(Prohibit modification and deletion)
