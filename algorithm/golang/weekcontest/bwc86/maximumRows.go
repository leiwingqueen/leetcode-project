package bwc86

// 相当于暴力解法了
func maximumRows(matrix [][]int, numSelect int) int {
	m := len(matrix)
	n := len(matrix[0])
	arr := make([]int, m)
	for i := 0; i < m; i++ {
		num := 0
		for j := 0; j < n; j++ {
			if matrix[i][j] == 1 {
				num |= 1 << j
			}
		}
		arr[i] = num
	}
	var dfs func(mask int, idx int, cnt int) int
	dfs = func(mask int, idx int, cnt int) int {
		if cnt > numSelect {
			return 0
		}
		if idx == n || cnt == numSelect {
			if cnt != numSelect {
				return 0
			}
			res := 0
			for i := 0; i < m; i++ {
				if (^mask)&arr[i] == 0 {
					res++
				}
			}
			return res
		}
		return max(dfs(mask, idx+1, cnt), dfs(mask|1<<idx, idx+1, cnt+1))
	}
	return dfs(0, 0, 0)
}

func max(a int, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}
