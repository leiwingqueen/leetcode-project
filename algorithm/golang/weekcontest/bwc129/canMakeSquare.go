package bwc129

//  显示英文描述
//通过的用户数1666
//尝试过的用户数1718
//用户总通过次数1685
//用户总提交次数2940
//题目难度Easy
//给你一个二维 3 x 3 的矩阵 grid ，每个格子都是一个字符，要么是 'B' ，要么是 'W' 。字符 'W' 表示白色，字符 'B' 表示黑色。
//
//你的任务是改变 至多一个 格子的颜色，使得矩阵中存在一个 2 x 2 颜色完全相同的正方形。
//如果可以得到一个相同颜色的 2 x 2 正方形，那么返回 true ，否则返回 false 。

func canMakeSquare(grid [][]byte) bool {
	check := func() bool {
		for i := 0; i < 2; i++ {
			for j := 0; j < 2; j++ {
				if grid[i][j] == grid[i+1][j] && grid[i][j] == grid[i][j+1] && grid[i][j] == grid[i+1][j+1] {
					return true
				}
			}
		}
		return false
	}
	if check() {
		return true
	}
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] == 'W' {
				grid[i][j] = 'B'
			} else {
				grid[i][j] = 'W'
			}
			if check() {
				return true
			}
			if grid[i][j] == 'W' {
				grid[i][j] = 'B'
			} else {
				grid[i][j] = 'W'
			}
		}
	}
	return false
}
