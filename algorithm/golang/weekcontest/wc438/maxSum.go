package wc438

import "sort"

// 给你一个大小为 n x m 的二维矩阵 grid ，以及一个长度为 n 的整数数组 limits ，和一个整数 k 。你的目标是从矩阵 grid 中提取出 至多 k 个元素，并计算这些元素的最大总和，提取时需满足以下限制：
//
// 从 grid 的第 i 行提取的元素数量不超过 limits[i] 。
//
// 返回最大总和。
//
// 示例 1：
//
// 输入：grid = [[1,2],[3,4]], limits = [1,2], k = 2
//
// 输出：7
//
// 解释：
//
// 从第 2 行提取至多 2 个元素，取出 4 和 3 。
// 至多提取 2 个元素时的最大总和 4 + 3 = 7 。
// 示例 2：
//
// 输入：grid = [[5,3,7],[8,2,6]], limits = [2,2], k = 3
//
// 输出：21
//
// 解释：
//
// 从第 1 行提取至多 2 个元素，取出 7 。
// 从第 2 行提取至多 2 个元素，取出 8 和 6 。
// 至多提取 3 个元素时的最大总和 7 + 8 + 6 = 21 。
//
// 提示：
//
// n == grid.length == limits.length
// m == grid[i].length
// 1 <= n, m <= 500
// 0 <= grid[i][j] <= 105
// 0 <= limits[i] <= m
// 0 <= k <= min(n * m, sum(limits))

// 使用大顶堆
func maxSum(grid [][]int, limits []int, k int) int64 {
	n := len(grid)
	var arr []int
	for i := 0; i < n; i++ {
		sort.Slice(grid[i], func(a, b int) bool {
			return grid[i][a] > grid[i][b]
		})
		arr = append(arr, grid[i][:limits[i]]...)
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i] > arr[j]
	})
	var res int64
	for i := 0; i < k; i++ {
		res += int64(arr[i])
	}
	return res
}

// 是不是可以使用类似归并排序的做法实现
func maxSum2(grid [][]int, limits []int, k int) int64 {
	n := len(grid)
	for i := 0; i < n; i++ {
		sort.Slice(grid[i], func(p1, p2 int) bool {
			return grid[i][p1] > grid[i][p2]
		})
	}
	p := make([]int, n)
	var res int64
	for i := 0; i < k; i++ {
		mx, idx := -1, -1
		for j := 0; j < n; j++ {
			if p[j] < limits[j] {
				if grid[j][p[j]] > mx {
					idx = j
					mx = grid[j][p[j]]
				}
			}
		}
		p[idx]++
		res += int64(mx)
	}
	return res
}
