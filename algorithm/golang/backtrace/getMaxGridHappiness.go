package backtrace

// 给你四个整数 m、n、introvertsCount 和 extrovertsCount 。有一个 m x n 网格，和两种类型的人：内向的人和外向的人。总共有 introvertsCount 个内向的人和 extrovertsCount 个外向的人。
//
//请你决定网格中应当居住多少人，并为每个人分配一个网格单元。 注意，不必 让所有人都生活在网格中。
//
//每个人的 幸福感 计算如下：
//
//内向的人 开始 时有 120 个幸福感，但每存在一个邻居（内向的或外向的）他都会 失去  30 个幸福感。
//外向的人 开始 时有 40 个幸福感，每存在一个邻居（内向的或外向的）他都会 得到  20 个幸福感。
//邻居是指居住在一个人所在单元的上、下、左、右四个直接相邻的单元中的其他人。
//
//网格幸福感 是每个人幸福感的 总和 。 返回 最大可能的网格幸福感 。
//
//
//
//示例 1：
//
//
//输入：m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
//输出：240
//解释：假设网格坐标 (row, column) 从 1 开始编号。
//将内向的人放置在单元 (1,1) ，将外向的人放置在单元 (1,3) 和 (2,3) 。
//- 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (0 * 30)（0 位邻居）= 120
//- 位于 (1,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60
//- 位于 (2,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60
//网格幸福感为：120 + 60 + 60 = 240
//上图展示该示例对应网格中每个人的幸福感。内向的人在浅绿色单元中，而外向的人在浅紫色单元中。
//示例 2：
//
//输入：m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
//输出：260
//解释：将内向的人放置在单元 (1,1) 和 (3,1) ，将外向的人放置在单元 (2,1) 。
//- 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
//- 位于 (2,1) 的外向的人的幸福感：40（初始幸福感）+ (2 * 20)（2 位邻居）= 80
//- 位于 (3,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
//网格幸福感为 90 + 80 + 90 = 260
//示例 3：
//
//输入：m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
//输出：240
//
//
//提示：
//
//1 <= m, n <= 5
//0 <= introvertsCount, extrovertsCount <= min(m * n, 6)
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximize-grid-happiness
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 先写个最直白的回溯，超时
func getMaxGridHappiness(m int, n int, introvertsCount int, extrovertsCount int) int {
	res := 0
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	iHapply, eHapply := 120, 40
	iCnt, eCnt := 30, 20
	var dfs func(s1 [][]bool, s2 [][]bool, cur int, iCount, eCount int)
	dfs = func(s1 [][]bool, s2 [][]bool, cur int, iCount, eCount int) {
		if cur > res {
			res = cur
		}
		if iCount <= 0 && eCount <= 0 {
			return
		}
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if s1[i][j] || s2[i][j] {
					continue
				}
				if iCount > 0 {
					s1[i][j] = true
					// 处理相邻的格子
					diff := iHapply
					for _, dir := range dirs {
						x, y := i+dir[0], j+dir[1]
						if x >= 0 && x < m && y >= 0 && y < n {
							if s1[x][y] {
								diff -= iCnt * 2
							} else if s2[x][y] {
								diff += eCnt - iCnt
							}
						}
					}
					dfs(s1, s2, cur+diff, iCount-1, eCount)
					s1[i][j] = false
				}
				if eCount > 0 {
					s2[i][j] = true
					// 处理相邻的格子
					diff := eHapply
					for _, dir := range dirs {
						x, y := i+dir[0], j+dir[1]
						if x >= 0 && x < m && y >= 0 && y < n {
							if s1[x][y] {
								diff += eCnt - iCnt
							} else if s2[x][y] {
								diff += eCnt * 2
							}
						}
					}
					dfs(s1, s2, cur+diff, iCount, eCount-1)
					s2[i][j] = false
				}
			}
		}
	}
	s1, s2 := make([][]bool, m), make([][]bool, m)
	for i := 0; i < m; i++ {
		s1[i] = make([]bool, n)
		s2[i] = make([]bool, n)
	}
	dfs(s1, s2, 0, introvertsCount, extrovertsCount)
	return res
}

// 增加记忆
func getMaxGridHappiness2(m int, n int, introvertsCount int, extrovertsCount int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	iHapply, eHapply := 120, 40
	iCnt, eCnt := 30, 20
	/*mem := make(map[string]int)
	makeKey := func(s1, s2 [][]bool) string {
		k1, k2 := 0, 0
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if s1[i][j] {
					k1 |= 1 << (i*m + j)
				}
				if s2[i][j] {
					k2 |= 1 << (i*m + j)
				}
			}
		}
		return fmt.Sprintf("%d#%d", k1, k2)
	}*/
	var dfs func(s1 [][]bool, s2 [][]bool, iCount, eCount int) int
	dfs = func(s1 [][]bool, s2 [][]bool, iCount, eCount int) int {
		if iCount <= 0 && eCount <= 0 {
			return 0
		}
		/**key := makeKey(s1, s2)
		if v, exist := mem[key]; exist {
			return v
		}*/
		res := 0
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if s1[i][j] || s2[i][j] {
					continue
				}
				if iCount > 0 {
					s1[i][j] = true
					// 处理相邻的格子
					diff := iHapply
					for _, dir := range dirs {
						x, y := i+dir[0], j+dir[1]
						if x >= 0 && x < m && y >= 0 && y < n {
							if s1[x][y] {
								diff -= iCnt * 2
							} else if s2[x][y] {
								diff += eCnt - iCnt
							}
						}
					}
					sub := dfs(s1, s2, iCount-1, eCount) + diff
					if sub > res {
						res = sub
					}
					s1[i][j] = false
				}
				if eCount > 0 {
					s2[i][j] = true
					// 处理相邻的格子
					diff := eHapply
					for _, dir := range dirs {
						x, y := i+dir[0], j+dir[1]
						if x >= 0 && x < m && y >= 0 && y < n {
							if s1[x][y] {
								diff += eCnt - iCnt
							} else if s2[x][y] {
								diff += eCnt * 2
							}
						}
					}
					sub := dfs(s1, s2, iCount, eCount-1) + diff
					if sub > res {
						res = sub
					}
					s2[i][j] = false
				}
			}
		}
		// mem[key] = res
		return res
	}
	s1, s2 := make([][]bool, m), make([][]bool, m)
	for i := 0; i < m; i++ {
		s1[i] = make([]bool, n)
		s2[i] = make([]bool, n)
	}
	return dfs(s1, s2, introvertsCount, extrovertsCount)
}

// 回溯优化一下，还是超时
func getMaxGridHappiness3(m int, n int, introvertsCount int, extrovertsCount int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	iHapply, eHapply := 120, 40
	iCnt, eCnt := 30, 20
	var dfs func(s [][]int, x, y, iCount, eCount int) int
	dfs = func(s [][]int, x, y, iCount, eCount int) int {
		if y >= n {
			x++
			y = 0
		}
		if x >= m {
			return 0
		}
		if iCount <= 0 && eCount <= 0 {
			return 0
		}
		// 不选
		res := dfs(s, x, y+1, iCount, eCount)
		if iCount > 0 {
			s[x][y] = 1
			diff := iHapply
			for _, dir := range dirs {
				nx, ny := x+dir[0], y+dir[1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n {
					if s[nx][ny] == 1 {
						diff -= iCnt * 2
					} else if s[nx][ny] == 2 {
						diff += eCnt - iCnt
					}
				}
			}
			sub := dfs(s, x, y+1, iCount-1, eCount) + diff
			s[x][y] = 0
			if sub > res {
				res = sub
			}
		}
		if eCount > 0 {
			s[x][y] = 2
			diff := eHapply
			for _, dir := range dirs {
				nx, ny := x+dir[0], y+dir[1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n {
					if s[nx][ny] == 1 {
						diff += eCnt - iCnt
					} else if s[nx][ny] == 2 {
						diff += eCnt * 2
					}
				}
			}
			sub := dfs(s, x, y+1, iCount, eCount-1) + diff
			s[x][y] = 0
			if sub > res {
				res = sub
			}
		}
		return res
	}
	s := make([][]int, m)
	for i := 0; i < m; i++ {
		s[i] = make([]int, n)
	}
	return dfs(s, 0, 0, introvertsCount, extrovertsCount)
}

// 实际上只跟上一行和当前行有关，所以我们可以简化状态存储
func getMaxGridHappiness4(m int, n int, introvertsCount int, extrovertsCount int) int {
	iHapply, eHapply := 120, 40
	iCnt, eCnt := 30, 20
	var dfs func(pre, cur int, x, y, iCount, eCount int) int
	dfs = func(pre, cur int, x, y, iCount, eCount int) int {
		if y >= n {
			x++
			y = 0
			pre = cur
			cur = 0
		}
		if x >= m {
			return 0
		}
		if iCount <= 0 && eCount <= 0 {
			return 0
		}
		// 不选
		res := dfs(pre, cur, x, y+1, iCount, eCount)
		if iCount > 0 {
			cur |= 1 << (2 * y)
			// s[x][y] = 1
			diff := iHapply
			// 上面格子
			if pre&(1<<(2*y)) != 0 {
				diff -= iCnt * 2
			} else if pre&(1<<(2*y+1)) != 0 {
				diff += eCnt - iCnt
			}
			sub := dfs(pre, cur, x, y+1, iCount-1, eCount) + diff
			cur ^= 1 << (2 * y)
			if sub > res {
				res = sub
			}
		}
		if eCount > 0 {
			cur |= 1 << (2*y + 1)
			diff := eHapply
			// 上面格子
			if pre&(1<<(2*y)) != 0 {
				diff += eCnt - iCnt
			} else if pre&(1<<(2*y+1)) != 0 {
				diff += eCnt * 2
			}
			sub := dfs(pre, cur, x, y+1, iCount, eCount-1) + diff
			cur ^= 1 << (2*y + 1)
			if sub > res {
				res = sub
			}
		}
		return res
	}
	s := make([][]int, m)
	for i := 0; i < m; i++ {
		s[i] = make([]int, n)
	}
	return dfs(0, 0, 0, 0, introvertsCount, extrovertsCount)
}
