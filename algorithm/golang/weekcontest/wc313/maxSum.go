package wc313

func maxSum(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	rows := make([]int, m)
	for i := 0; i < m; i++ {
		sum := 0
		for j := 0; j < n; j++ {
			sum += grid[i][j]
		}
		rows[i] = sum
	}
	prefix := make([][]int, n)
	for i := 0; i < n; i++ {
		prefix[i] = make([]int, m+1)
		for j := 0; j < m; j++ {
			prefix[i][j+1] = prefix[i][j] + grid[j][i]
		}
	}
	cols := make([][]int, m)
	for i := 0; i < m; i++ {
		cols[i] = make([]int, m)
	}
	for i := 0; i < m; i++ {
		for j := i; j < m; j++ {
			//行号在[i,j]之间的最大值
			mx := 0
			for k := 0; k < n; k++ {
				s := prefix[k][j+1] - prefix[k][i]
				if s > mx {
					mx = s
				}
			}
			cols[i][j] = mx
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := i + 2; j < m; j++ {
			s := rows[i] + rows[j] + cols[i+1][j-1]
			if s > res {
				res = s
			}
		}
	}
	return res
}

// 上面题意理解错了
func maxSum2(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	res := 0
	for i := 0; i < m-2; i++ {
		for j := 0; j < n-2; j++ {
			sum := grid[i][j] + grid[i][j+1] + grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j] + grid[i+2][j+1] + grid[i+2][j+2]
			if sum > res {
				res = sum
			}
		}
	}
	return res
}
