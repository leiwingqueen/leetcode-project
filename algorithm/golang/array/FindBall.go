package array

//1706. 球会落何处
//用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
//
//箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
//
//将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
//将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
//在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
//
//返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
//
//
//
//示例 1：
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/where-will-the-ball-fall
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findBall(grid [][]int) []int {
	m := len(grid)
	n := len(grid[0])
	res := make([]int, n)
	for i := 0; i < n; i++ {
		res[i] = i
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			pos := res[j]
			if pos >= 0 {
				//下一层的下标
				np := pos + grid[i][pos]
				if np >= 0 && np < n && grid[i][np] == grid[i][pos] {
					res[j] = np
				} else {
					res[j] = -1
				}
			}
		}
	}
	return res
}
