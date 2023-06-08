package backtrace

//你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
//
// 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
//
// 假设正方形瓷砖的规格不限，边长都是整数。
//
// 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
//
//
//
// 示例 1：
//
//
//
// 输入：n = 2, m = 3
//输出：3
//解释：3 块地砖就可以铺满卧室。
//     2 块 1x1 地砖
//     1 块 2x2 地砖
//
// 示例 2：
//
//
//
// 输入：n = 5, m = 8
//输出：5
//
//
// 示例 3：
//
//
//
// 输入：n = 11, m = 13
//输出：6
//
//
//
//
// 提示：
//
//
// 1 <= n <= 13
// 1 <= m <= 13
//
//
// Related Topics 动态规划 回溯 👍 165 👎 0

// 回溯解法，超时
func tilingRectangle(n int, m int) int {
	matrix := make([][]bool, n)
	for i := 0; i < n; i++ {
		matrix[i] = make([]bool, m)
	}
	hash := func(arr [][]bool) int {
		res := 0
		idx := 0
		for i := 0; i < n; i++ {
			for j := 0; j < m; j++ {
				if arr[i][j] {
					res |= 1 << idx
				}
				idx++
			}
		}
		return res
	}
	mem := make(map[int]int)
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		key := hash(matrix)
		if v, exist := mem[key]; exist {
			return v
		}
		if x == n-1 && y == m-1 {
			if !matrix[x][y] {
				return 1
			} else {
				return 0
			}
		}
		if matrix[x][y] {
			y++
			if y == m {
				x++
				y = 0
			}
			return dfs(x, y)
		} else {
			res := m * n
			for k := 1; y+k-1 < m && x+k-1 < n; k++ {
				if matrix[x+k-1][y+k-1] {
					break
				}
				// 更细矩阵
				for i := x; i <= x+k-1; i++ {
					for j := y; j <= y+k-1; j++ {
						matrix[i][j] = true
					}
				}
				ny := y + 1
				nx := x
				if y+1 == m {
					nx++
					ny = 0
				}
				sub := dfs(nx, ny) + 1
				// 还原现场
				for i := x; i <= x+k-1; i++ {
					for j := y; j <= y+k-1; j++ {
						matrix[i][j] = false
					}
				}
				if sub < res {
					res = sub
				}
			}
			mem[key] = res
			return res
		}
	}
	return dfs(0, 0)
}
