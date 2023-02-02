package bfs

// 在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
//
//red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。
//
//返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
//
//
//
//示例 1：
//
//输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//输出：[0,1,-1]
//示例 2：
//
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//输出：[0,1,-1]
//示例 3：
//
//输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//输出：[0,-1,-1]
//示例 4：
//
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//输出：[0,1,2]
//示例 5：
//
//输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//输出：[0,1,1]
//
//
//提示：
//
//1 <= n <= 100
//red_edges.length <= 400
//blue_edges.length <= 400
//red_edges[i].length == blue_edges[i].length == 2
//0 <= red_edges[i][j], blue_edges[i][j] < n
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-path-with-alternating-colors
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func shortestAlternatingPaths(n int, redEdges [][]int, blueEdges [][]int) []int {
	redGraph := make([][]int, n)
	blueGraph := make([][]int, n)
	for _, edge := range redEdges {
		x := edge[0]
		y := edge[1]
		redGraph[x] = append(redGraph[x], y)
		redGraph[y] = append(redGraph[y], x)
	}
	for _, edge := range blueEdges {
		x := edge[0]
		y := edge[1]
		blueGraph[x] = append(blueGraph[x], y)
		blueGraph[y] = append(blueGraph[y], x)
	}
	bfs := func(color int) []int {
		res := make([]int, n)
		for i := 0; i < n; i++ {
			res[i] = -1
		}
		queue := [][]int{{0, color}}
		visit := make([][]bool, n)
		for i := 0; i < n; i++ {
			visit[i] = []bool{false, false}
		}
		visit[0][color] = true
		depth := 0
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				pop := queue[i]
				if res[pop[0]] < 0 {
					res[pop[0]] = depth
				}
				if res[1] == 0 {
					for _, next := range blueGraph[pop[0]] {
						if !visit[next][1] {
							queue = append(queue, []int{next, 1})
							visit[next][1] = true
						}
					}
				} else {
					for _, next := range redGraph[pop[1]] {
						if !visit[next][0] {
							queue = append(queue, []int{next, 0})
							visit[next][0] = true
						}
					}
				}
			}
			depth++
			queue = queue[size:]
		}
		return res
	}
	r1 := bfs(0)
	r2 := bfs(1)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		if r1[i] < 0 {
			res[i] = r2[i]
		} else if r2[i] < 0 {
			res[i] = r1[i]
		} else {
			if r1[i] < r2[i] {
				res[i] = r1[i]
			} else {
				res[i] = r2[i]
			}
		}
	}
	return res
}
