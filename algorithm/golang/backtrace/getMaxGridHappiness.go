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

// 先写个最直白的回溯
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
				if !s1[i][j] && !s2[i][j] {
					if iCount > 0 {
						s1[i][j] = true
						// 处理相邻的格子
						diff := 0
						for _, dir := range dirs {
							x, y := i+dir[0], j+dir[1]
							cur += iHapply
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
						diff := 0
						for _, dir := range dirs {
							x, y := i+dir[0], j+dir[1]
							cur += eHapply
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
	}
	s1, s2 := make([][]bool, m), make([][]bool, m)
	for i := 0; i < m; i++ {
		s1[i] = make([]bool, n)
		s2[i] = make([]bool, n)
	}
	dfs(s1, s2, 0, introvertsCount, extrovertsCount)
	return res
}
