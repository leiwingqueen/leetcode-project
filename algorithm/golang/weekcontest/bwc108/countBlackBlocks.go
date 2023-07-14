package bwc108

// 给你两个整数 m 和 n ，表示一个下标从 0 开始的 m x n 的网格图。
//
//给你一个下标从 0 开始的二维整数矩阵 coordinates ，其中 coordinates[i] = [x, y] 表示坐标为 [x, y] 的格子是 黑色的 ，所有没出现在 coordinates 中的格子都是 白色的。
//
//一个块定义为网格图中 2 x 2 的一个子矩阵。更正式的，对于左上角格子为 [x, y] 的块，其中 0 <= x < m - 1 且 0 <= y < n - 1 ，包含坐标为 [x, y] ，[x + 1, y] ，[x, y + 1] 和 [x + 1, y + 1] 的格子。
//
//请你返回一个下标从 0 开始长度为 5 的整数数组 arr ，arr[i] 表示恰好包含 i 个 黑色 格子的块的数目。
//
//
//
//示例 1：
//
//输入：m = 3, n = 3, coordinates = [[0,0]]
//输出：[3,1,0,0,0]
//解释：网格图如下：
//
//只有 1 个块有一个黑色格子，这个块是左上角为 [0,0] 的块。
//其他 3 个左上角分别为 [0,1] ，[1,0] 和 [1,1] 的块都有 0 个黑格子。
//所以我们返回 [3,1,0,0,0] 。
//示例 2：
//
//输入：m = 3, n = 3, coordinates = [[0,0],[1,1],[0,2]]
//输出：[0,2,2,0,0]
//解释：网格图如下：
//
//有 2 个块有 2 个黑色格子（左上角格子分别为 [0,0] 和 [0,1]）。
//左上角为 [1,0] 和 [1,1] 的两个块，都有 1 个黑格子。
//所以我们返回 [0,2,2,0,0] 。
//
//
//提示：
//
//2 <= m <= 105
//2 <= n <= 105
//0 <= coordinates.length <= 104
//coordinates[i].length == 2
//0 <= coordinates[i][0] < m
//0 <= coordinates[i][1] < n
//coordinates 中的坐标对两两互不相同。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-black-blocks
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 这么查肯定超时
func countBlackBlocks(m int, n int, coordinates [][]int) []int64 {
	mp := make(map[int64]bool)
	for _, pos := range coordinates {
		x, y := pos[0], pos[1]
		mp[int64(x<<32)|int64(y)] = true
	}
	res := make([]int64, 5)
	for i := 0; i < m-1; i++ {
		for j := 0; j < n-1; j++ {
			cnt := 0
			if mp[int64(i)<<32|int64(j)] {
				cnt++
			}
			if mp[int64(i)<<32|int64(j+1)] {
				cnt++
			}
			if mp[int64(i+1)<<32|int64(j)] {
				cnt++
			}
			if mp[int64(i+1)<<32|int64(j+1)] {
				cnt++
			}
			res[cnt]++
		}
	}
	return res
}

// 通过了
func countBlackBlocks2(m int, n int, coordinates [][]int) []int64 {
	mp := make(map[int64]bool)
	for _, pos := range coordinates {
		x, y := pos[0], pos[1]
		mp[int64(x<<32)|int64(y)] = true
	}
	cal := func(i, j int) int {
		cnt := 0
		if mp[int64(i)<<32|int64(j)] {
			cnt++
		}
		if mp[int64(i)<<32|int64(j+1)] {
			cnt++
		}
		if mp[int64(i+1)<<32|int64(j)] {
			cnt++
		}
		if mp[int64(i+1)<<32|int64(j+1)] {
			cnt++
		}
		return cnt
	}
	visit := make(map[int64]bool)
	res := make([]int64, 5)
	c := 0
	for _, co := range coordinates {
		i, j := co[0], co[1]
		// 左上角
		if i < m-1 && j < n-1 {
			x, y := i, j
			if !visit[int64(x<<32)|int64(y)] {
				cnt := cal(x, y)
				res[cnt]++
				visit[int64(x<<32)|int64(y)] = true
				c++
			}
		}
		// 右上角
		if i < m-1 && j > 0 {
			x, y := i, j-1
			if !visit[int64(x<<32)|int64(y)] {
				cnt := cal(x, y)
				res[cnt]++
				visit[int64(x<<32)|int64(y)] = true
				c++
			}
		}
		// 左下角
		if i > 0 && j < n-1 {
			x, y := i-1, j
			if !visit[int64(x<<32)|int64(y)] {
				cnt := cal(x, y)
				res[cnt]++
				visit[int64(x<<32)|int64(y)] = true
				c++
			}
		}
		// 右下角
		if i > 0 && j > 0 {
			x, y := i-1, j-1
			if !visit[int64(x<<32)|int64(y)] {
				cnt := cal(x, y)
				res[cnt]++
				visit[int64(x<<32)|int64(y)] = true
				c++
			}
		}
	}
	// 一个都不包含的子矩阵数量
	total := int64(m)*int64(n) - int64(m+n-1)
	res[0] = total - int64(c)
	return res
}
