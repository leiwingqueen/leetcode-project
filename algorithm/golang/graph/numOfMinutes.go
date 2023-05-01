package graph

func numOfMinutes(n int, headID int, manager []int, informTime []int) int {
	graph := make([][]int, n)
	for i, leader := range manager {
		if leader >= 0 {
			graph[leader] = append(graph[leader], i)
		}
	}
	var depth func(start int) int
	depth = func(start int) int {
		res := 0
		for _, child := range graph[start] {
			sub := depth(child) + informTime[start]
			if sub > res {
				res = sub
			}
		}
		return res
	}
	return depth(headID)
}
