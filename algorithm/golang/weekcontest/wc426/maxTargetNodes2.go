package wc426

// 超时
func maxTargetNodes2(edges1 [][]int, edges2 [][]int) []int {
	n, m := len(edges1)+1, len(edges2)+1
	graph1, graph2 := make([][]int, n), make([][]int, m)
	for _, edge := range edges1 {
		x, y := edge[0], edge[1]
		graph1[x] = append(graph1[x], y)
		graph1[y] = append(graph1[y], x)
	}
	for _, edge := range edges2 {
		x, y := edge[0], edge[1]
		graph2[x] = append(graph2[x], y)
		graph2[y] = append(graph2[y], x)
	}
	var dfs func(node int, graph [][]int, parent int, depth int) (int, int)
	dfs = func(node int, graph [][]int, parent int, depth int) (int, int) {
		even, odd := 0, 0
		if depth%2 == 0 {
			even++
		} else {
			odd++
		}
		for _, next := range graph[node] {
			if next != parent {
				e, o := dfs(next, graph, node, depth+1)
				even += e
				odd += o
			}
		}
		return even, odd
	}
	even, odd := dfs(0, graph2, -1, 0)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		r, _ := dfs(i, graph1, -1, 0)
		r += max(even, odd)
		res[i] = r
	}
	return res
}

func maxTargetNodes3(edges1 [][]int, edges2 [][]int) []int {
	n, m := len(edges1)+1, len(edges2)+1
	graph1, graph2 := make([][]int, n), make([][]int, m)
	for _, edge := range edges1 {
		x, y := edge[0], edge[1]
		graph1[x] = append(graph1[x], y)
		graph1[y] = append(graph1[y], x)
	}
	for _, edge := range edges2 {
		x, y := edge[0], edge[1]
		graph2[x] = append(graph2[x], y)
		graph2[y] = append(graph2[y], x)
	}
	var dfs func(node int, graph [][]int, parent int, depth int, black []bool) (int, int)
	dfs = func(node int, graph [][]int, parent int, depth int, black []bool) (int, int) {
		even, odd := 0, 0
		if depth%2 == 0 {
			even++
			black[node] = true
		} else {
			odd++
		}
		for _, next := range graph[node] {
			if next != parent {
				e, o := dfs(next, graph, node, depth+1, black)
				even += e
				odd += o
			}
		}
		return even, odd
	}
	even, odd := dfs(0, graph2, -1, 0, make([]bool, m))
	black := make([]bool, n)
	even1, odd1 := dfs(0, graph1, -1, 0, black)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		r := max(even, odd)
		if black[i] {
			r += even1
		} else {
			r += odd1
		}
		res[i] = r
	}
	return res
}
