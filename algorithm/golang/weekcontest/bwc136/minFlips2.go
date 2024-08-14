package bwc136

func minFlips2(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	visit := make([][]bool, m)
	for i := 0; i < m; i++ {
		visit[i] = make([]bool, n)
	}
	queue := [][]int{
		{0, 0},
	}
	visit[0][0] = true
	dirs := [][]int{
		{0, 1},
		{1, 0},
	}
	findNode := func(x, y int) [][]int {
		var nodes [][]int
		nodes = append(nodes, []int{x, y})
		if n-y-1 != y {
			nodes = append(nodes, []int{x, n - y - 1})
		}
		if m-1-x != x {
			nodes = append(nodes, []int{m - 1 - x, y})
		}
		if n-y-1 != y && m-1-x != x {
			nodes = append(nodes, []int{m - 1 - x, n - 1 - y})
		}
		return nodes
	}
	res := 0
	total := 0
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		nodes := findNode(node[0], node[1])
		if len(nodes) == 1 {
			if grid[node[0]][node[1]] == 1 {
				res++
			}
		} else if len(nodes) == 2 {
			cnt := 0
			for _, d := range nodes {
				if grid[d[0]][d[1]] == 1 {
					cnt++
				}
			}
			if cnt == 1 {
				res++
			} else if cnt == 2 {
				total += 2
			}
		} else {
			cnt := 0
			for _, d := range nodes {
				if grid[d[0]][d[1]] == 1 {
					cnt++
				}
			}
			if cnt == 1 || cnt == 3 {
				res++
			} else if cnt == 2 {
				res += 2
				total += 2
			}
		}
		for _, d := range nodes {
			visit[d[0]][d[1]] = true
		}
		for _, dir := range dirs {
			x, y := node[0]+dir[0], node[1]+dir[1]
			if x < m && y < n && !visit[x][y] {
				queue = append(queue, []int{x, y})
				visit[x][y] = true
			}
		}
	}
	return res
}
