package wc347

import (
	"sort"
)

// 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
//
//从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
//
//你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
//
//请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
//
//返回一个表示可访问单元格最大数量的整数。
//
//
//
//示例 1：
//
//
//
//输入：mat = [[3,1],[3,4]]
//输出：2
//解释：上图展示了从第 1 行、第 2 列的单元格开始，可以访问 2 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 2 个单元格，因此答案是 2 。
//示例 2：
//
//
//
//输入：mat = [[1,1],[1,1]]
//输出：1
//解释：由于目标单元格必须严格大于当前单元格，在本示例中只能访问 1 个单元格。
//示例 3：
//
//
//
//输入：mat = [[3,1,6],[-9,5,7]]
//输出：4
//解释：上图展示了从第 2 行、第 1 列的单元格开始，可以访问 4 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 4 个单元格，因此答案是 4 。
//
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 105
//1 <= m * n <= 105
//-105 <= mat[i][j] <= 105

func maxIncreasingCells(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	rows := make([][]int, m)
	for i := 0; i < m; i++ {
		rows[i] = make([]int, n)
		for j := 0; j < n; j++ {
			rows[i][j] = j
		}
		sort.Slice(rows[i], func(l, k int) bool {
			c1, c2 := rows[i][l], rows[i][k]
			return mat[i][c1] < mat[i][c2]
		})
	}
	cols := make([][]int, n)
	for i := 0; i < n; i++ {
		cols[i] = make([]int, m)
		for j := 0; j < m; j++ {
			cols[i][j] = j
		}
		sort.Slice(cols[i], func(l, k int) bool {
			r1, r2 := cols[i][l], cols[i][k]
			return mat[r1][i] < mat[r2][i]
		})
	}
	cache := make([][]int, m)
	for i := 0; i < m; i++ {
		cache[i] = make([]int, n)
		for j := 0; j < n; j++ {
			cache[i][j] = -1
		}
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		// fmt.Println(fmt.Sprintf("[%d,%d]", x, y))
		if cache[x][y] >= 0 {
			return cache[x][y]
		}
		// 找到最小能移动的列的值，二分查找，这里要注意的一点是，如果存在多个值相等的列，那么我们需要都遍历一遍，然后取最大值
		sub1 := 0
		c := sort.Search(n, func(i int) bool {
			idx := rows[x][i]
			return mat[x][idx] > mat[x][y]
		})
		if c == n {
			sub1 = 1
		} else {
			i := c
			for i < n && mat[x][rows[x][i]] == mat[x][rows[x][c]] {
				s := dfs(x, rows[x][i]) + 1
				if s > sub1 {
					sub1 = s
				}
				i++
			}
		}
		sub2 := 0
		r := sort.Search(m, func(i int) bool {
			idx := cols[y][i]
			return mat[idx][y] > mat[x][y]
		})
		if r == m {
			sub2 = 1
		} else {
			i := r
			for i < m && mat[cols[y][i]][y] == mat[cols[y][r]][y] {
				s := dfs(cols[y][i], y) + 1
				if s > sub2 {
					sub2 = s
				}
				i++
			}
		}
		if sub1 > sub2 {
			cache[x][y] = sub1
			return sub1
		} else {
			cache[x][y] = sub2
			return sub2
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			sub := dfs(i, j)
			if sub > res {
				res = sub
			}
		}
	}
	return res

}

func maxIncreasingCells2(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	rows := make([]int, m)
	cols := make([]int, n)
	mp := make(map[int][][2]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			v := mat[i][j]
			mp[v] = append(mp[v], [2]int{i, j})
		}
	}
	var arr []int
	for k := range mp {
		arr = append(arr, k)
	}
	sort.Ints(arr)
	for _, num := range arr {
		pos := mp[num]
		// 相同值的位置需要同时计算
		var tmp []int
		for _, p := range pos {
			x, y := p[0], p[1]
			tmp = append(tmp, max(rows[x], cols[y])+1)
		}
		// 同时更新rows和cols
		for i, p := range pos {
			x, y := p[0], p[1]
			rows[x] = max(rows[x], tmp[i])
			cols[y] = max(cols[y], tmp[i])
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		res = max(res, rows[i])
	}
	return res
}
