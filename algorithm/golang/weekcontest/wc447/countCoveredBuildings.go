package wc447

import "sort"

// 给你一个正整数 n，表示一个 n x n 的城市，同时给定一个二维数组 buildings，其中 buildings[i] = [x, y] 表示位于坐标 [x, y] 的一个 唯一 建筑。
//
//如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑 被覆盖 。
//
//返回 被覆盖 的建筑数量。
//
//
//
//示例 1：
//
//
//
//输入: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
//
//输出: 1
//
//解释:
//
//只有建筑 [2,2] 被覆盖，因为它在每个方向上都至少存在一个建筑：
//上方 ([1,2])
//下方 ([3,2])
//左方 ([2,1])
//右方 ([2,3])
//因此，被覆盖的建筑数量是 1。
//示例 2：
//
//
//
//输入: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
//
//输出: 0
//
//解释:
//
//没有任何一个建筑在每个方向上都有至少一个建筑。
//示例 3：
//
//
//
//输入: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
//
//输出: 1
//
//解释:
//
//只有建筑 [3,3] 被覆盖，因为它在每个方向上至少存在一个建筑：
//上方 ([1,3])
//下方 ([5,3])
//左方 ([3,2])
//右方 ([3,5])
//因此，被覆盖的建筑数量是 1。
//
//
//提示：
//
//2 <= n <= 105
//1 <= buildings.length <= 105
//buildings[i] = [x, y]
//1 <= x, y <= n
//buildings 中所有坐标均 唯一 。

// 先考虑一个问题，如何判断坐标的左和下被包围?
// 假设我们先按x坐标排序，然后再按y坐标排序
func countCoveredBuildings(n int, buildings [][]int) int {
	rows := make(map[int][]int)
	cols := make(map[int][]int)
	for i := range buildings {
		x, y := buildings[i][0], buildings[i][1]
		rows[x] = append(rows[x], i)
		cols[y] = append(cols[y], i)
	}
	// 排序
	for x := range rows {
		sort.Slice(rows[x], func(i, j int) bool {
			b1, b2 := buildings[rows[x][i]], buildings[rows[x][j]]
			return b1[1] < b2[1]
		})
	}
	for y := range cols {
		sort.Slice(cols[y], func(i, j int) bool {
			b1, b2 := buildings[cols[y][i]], buildings[cols[y][j]]
			return b1[0] < b2[0]
		})
	}
	degree := make([]int, len(buildings))
	for x := range rows {
		if len(rows[x]) < 3 {
			continue
		}
		for _, i := range rows[x][1 : len(rows[x])-1] {
			degree[i]++
		}
	}
	for y := range cols {
		if len(cols[y]) < 3 {
			continue
		}
		for _, i := range cols[y][1 : len(cols[y])-1] {
			degree[i]++
		}
	}
	res := 0
	for i := range degree {
		if degree[i] == 2 {
			res++
		}
	}
	return res
}
