package bfs

import "math"

// There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
//
//Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
//
//Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
//
//
//
//Example 1:
//
//
//Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
//Output: 3
//Explanation: The figure above describes the graph.
//The neighboring cities at a distanceThreshold = 4 for each city are:
//City 0 -> [City 1, City 2]
//City 1 -> [City 0, City 2, City 3]
//City 2 -> [City 0, City 1, City 3]
//City 3 -> [City 1, City 2]
//Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
//Example 2:
//
//
//Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
//Output: 0
//Explanation: The figure above describes the graph.
//The neighboring cities at a distanceThreshold = 2 for each city are:
//City 0 -> [City 1]
//City 1 -> [City 0, City 4]
//City 2 -> [City 3, City 4]
//City 3 -> [City 2, City 4]
//City 4 -> [City 1, City 2, City 3]
//The city 0 has 1 neighboring city at a distanceThreshold = 2.
//
//
//Constraints:
//
//2 <= n <= 100
//1 <= edges.length <= n * (n - 1) / 2
//edges[i].length == 3
//0 <= fromi < toi < n
//1 <= weighti, distanceThreshold <= 10^4
//All pairs (fromi, toi) are distinct.

type Edge struct {
	to     int
	weight int
}

func findTheCity(n int, edges [][]int, distanceThreshold int) int {
	graph := make([][]Edge, n)
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		graph[x] = append(graph[x], Edge{y, w})
		graph[y] = append(graph[y], Edge{x, w})
	}
	bfs := func(start int) int {
		queue := []int{start}
		dis := make([]int, n)
		for i := 0; i < n; i++ {
			dis[i] = distanceThreshold + 1
		}
		dis[start] = 0
		for len(queue) > 0 {
			node := queue[0]
			for _, next := range graph[node] {
				if dis[node]+next.weight <= distanceThreshold &&
					dis[next.to] > dis[node]+next.weight {
					dis[next.to] = dis[node] + next.weight
					queue = append(queue, next.to)
				}
			}
			queue = queue[1:]
		}
		cnt := 0
		for i := 0; i < n; i++ {
			if dis[i] <= distanceThreshold {
				cnt++
			}
		}
		return cnt - 1
	}
	res := -1
	min := n
	for i := 0; i < n; i++ {
		d := bfs(i)
		if d <= min {
			res = i
			min = d
		}
	}
	return res
}

// floyd算法
func findTheCity2(n int, edges [][]int, distanceThreshold int) int {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	dis := make([][]int, n)
	for i := 0; i < n; i++ {
		dis[i] = make([]int, n)
		for j := 0; j < n; j++ {
			dis[i][j] = math.MaxInt / 2
		}
		dis[i][i] = 0
	}
	for _, edge := range edges {
		x, y, w := edge[0], edge[1], edge[2]
		dis[x][y] = w
		dis[y][x] = w
	}
	for k := 0; k < n; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				dis[i][j] = min(dis[i][j], dis[i][k]+dis[k][j])
			}
		}
	}
	res := -1
	m := math.MaxInt
	for i := 0; i < n; i++ {
		cnt := 0
		for j := 0; j < n; j++ {
			if dis[i][j] <= distanceThreshold {
				cnt++
			}
		}
		if cnt <= m {
			res = i
			m = cnt
		}
	}
	return res
}
