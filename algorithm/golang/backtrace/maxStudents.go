package backtrace

// 给你一个 m * n 的矩阵 seats 表示教室中的座位分布。如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
//
//学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的同时参加考试且无法作弊的 最大 学生人数。
//
//学生必须坐在状况良好的座位上。
//
//
//
//示例 1：
//
//
//
//输入：seats = [["#",".","#","#",".","#"],
//              [".","#","#","#","#","."],
//              ["#",".","#","#",".","#"]]
//输出：4
//解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。
//示例 2：
//
//输入：seats = [[".","#"],
//              ["#","#"],
//              ["#","."],
//              ["#","#"],
//              [".","#"]]
//输出：3
//解释：让所有学生坐在可用的座位上。
//示例 3：
//
//输入：seats = [["#",".",".",".","#"],
//              [".","#",".","#","."],
//              [".",".","#",".","."],
//              [".","#",".","#","."],
//              ["#",".",".",".","#"]]
//输出：10
//解释：让学生坐在第 1、3 和 5 列的可用座位上。
//
//
//提示：
//
//seats 只包含字符 '.' 和'#'
//m == seats.length
//n == seats[i].length
//1 <= m <= 8
//1 <= n <= 8

// 暴力回溯，先验证逻辑OK，超时
func maxStudents(seats [][]byte) int {
	m, n := len(seats), len(seats[0])
	dirs := [][]int{
		{0, -1},
		{0, 1},
		{-1, -1},
		{-1, 1},
	}
	var dfs func(state int64, x, y int) int
	dfs = func(state int64, x, y int) int {
		if y == n {
			x++
			y = 0
		}
		if x == m {
			return 0
		}
		if seats[x][y] == '#' {
			return dfs(state, x, y+1)
		}
		// 不坐人的场景
		res := dfs(state, x, y+1)
		// 先检查能不能坐人
		flag := true
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && (state&(1<<(nx*n+ny))) != 0 {
				flag = false
				break
			}
		}
		if flag {
			sub := dfs(state|(1<<(x*n+y)), x, y+1) + 1
			if sub > res {
				res = sub
			}
		}
		return res
	}
	return dfs(0, 0, 0)
}

// 这样写还是会超时
func maxStudents2(seats [][]byte) int {
	m, n := len(seats), len(seats[0])
	dirs := [][]int{
		{0, -1},
		{0, 1},
		{-1, -1},
		{-1, 1},
	}
	mem := make(map[int]map[int64]int)
	var dfs func(state int64, x, y int) int
	dfs = func(state int64, x, y int) int {
		if y == n {
			x++
			y = 0
		}
		if x == m {
			return 0
		}
		if _, ok := mem[x*n+y]; !ok {
			mem[x*n+y] = make(map[int64]int)
		}
		if v, ok := mem[x*n+y][state]; ok {
			return v
		}
		res := dfs(state, x, y+1)
		defer func() {
			mem[x*n+y][state] = res
		}()
		if seats[x][y] == '#' {
			return res
		}
		// 先检查能不能坐人
		flag := true
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && (state&(1<<(nx*n+ny))) != 0 {
				flag = false
				break
			}
		}
		if flag {
			sub := dfs(state|(1<<(x*n+y)), x, y+1) + 1
			if sub > res {
				res = sub
			}
		}
		return res
	}
	return dfs(0, 0, 0)
}
