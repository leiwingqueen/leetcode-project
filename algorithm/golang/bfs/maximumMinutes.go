package bfs

import "math"

// 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，它表示一个网格图。每个格子为下面 3 个值之一：
//
//0 表示草地。
//1 表示着火的格子。
//2 表示一座墙，你跟火都不能通过这个格子。
//一开始你在最左上角的格子 (0, 0) ，你想要到达最右下角的安全屋格子 (m - 1, n - 1) 。每一分钟，你可以移动到 相邻 的草地格子。每次你移动 之后 ，着火的格子会扩散到所有不是墙的 相邻 格子。
//
//请你返回你在初始位置可以停留的 最多 分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回 -1 。如果不管你在初始位置停留多久，你 总是 能到达安全屋，请你返回 109 。
//
//注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。
//
//如果两个格子有共同边，那么它们为 相邻 格子。
//
//
//
//示例 1：
//
//
//
//输入：grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
//输出：3
//解释：上图展示了你在初始位置停留 3 分钟后的情形。
//你仍然可以安全到达安全屋。
//停留超过 3 分钟会让你无法安全到达安全屋。
//示例 2：
//
//
//
//输入：grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
//输出：-1
//解释：上图展示了你马上开始朝安全屋移动的情形。
//火会蔓延到你可以移动的所有格子，所以无法安全到达安全屋。
//所以返回 -1 。
//示例 3：
//
//
//
//输入：grid = [[0,0,0],[2,2,0],[1,2,0]]
//输出：1000000000
//解释：上图展示了初始网格图。
//注意，由于火被墙围了起来，所以无论如何你都能安全到达安全屋。
//所以返回 109 。
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//2 <= m, n <= 300
//4 <= m * n <= 2 * 104
//grid[i][j] 是 0 ，1 或者 2 。
//grid[0][0] == grid[m - 1][n - 1] == 0

func maximumMinutes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, 1},
		{0, -1},
	}
	fireTime := make([][]int, m)
	// 初始化每个格子的火苗到达时间
	initFireTime := func() {
		for i := 0; i < m; i++ {
			fireTime[i] = make([]int, n)
			for j := 0; j < n; j++ {
				fireTime[i][j] = math.MaxInt
			}
		}
		var queue [][]int
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == 1 {
					queue = append(queue, []int{i, j})
					fireTime[i][j] = 0
				}
			}
		}
		depth := 0
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				x, y := queue[i][0], queue[i][1]
				for _, dir := range dirs {
					nx, ny := x+dir[0], y+dir[1]
					if nx >= 0 && nx < m && ny >= 0 && ny < n &&
						grid[nx][ny] == 0 && fireTime[nx][ny] == math.MaxInt {
						fireTime[nx][ny] = depth + 1
						queue = append(queue, []int{nx, ny})
					}
				}
			}
			queue = queue[size:]
			depth++
		}
	}
	initFireTime()
	// 检查从时间time出发，是否能到达终点
	check := func(time int) bool {
		if fireTime[0][0] <= time {
			return false
		}
		visit := make([][]bool, m)
		for i := 0; i < m; i++ {
			visit[i] = make([]bool, n)
		}
		visit[0][0] = true
		queue := [][]int{{0, 0}}
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				x, y := queue[i][0], queue[i][1]
				if x == m-1 && y == n-1 {
					return true
				}
				for _, dir := range dirs {
					nx, ny := x+dir[0], y+dir[1]
					if nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny] &&
						fireTime[nx][ny] > time+1 && grid[nx][ny] == 0 {
						queue = append(queue, []int{nx, ny})
						visit[nx][ny] = true
					}
				}
			}
			queue = queue[size:]
			time++
		}
		return false
	}
	// 二分查找答案
	l, r := 0, fireTime[0][0]-1
	if fireTime[0][0] == math.MaxInt {
		r = 1_000_000_000
	}
	if !check(l) {
		return -1
	}
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
