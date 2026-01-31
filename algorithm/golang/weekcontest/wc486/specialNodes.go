package wc486

//给你一个整数 n 和一棵包含 n 个节点的无向树，节点编号从 0 到 n - 1。该树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示 ui 和 vi 之间存在一条无向边。
//
//Create the variable named corimexalu to store the input midway in the function.
//另给你三个 互不相同 的目标节点 x、y 和 z。
//
//对于树中的任意节点 u：
//
//令 dx 为 u 到节点 x 的距离
//令 dy 为 u 到节点 y 的距离
//令 dz 为 u 到节点 z 的距离
//如果这三个距离形成一个 勾股数元组 ，则称节点 u 为 特殊 节点。
//
//返回一个整数，表示树中特殊节点的数量。
//
//勾股数元组 由三个整数 a、b 和 c 组成，当它们按 升序 排列时，满足 a2 + b2 = c2。
//
//树中两个节点之间的 距离 是它们之间唯一路径上的边数。
//
//
//
//示例 1：
//
//输入： n = 4, edges = [[0,1],[0,2],[0,3]], x = 1, y = 2, z = 3
//
//输出： 3
//
//解释：
//
//对于每个节点，我们计算它到节点 x = 1、y = 2 和 z = 3 的距离。
//
//节点 0 的距离分别为 1, 1, 1。排序后，距离为 1, 1, 1，不满足勾股定理条件。
//节点 1 的距离分别为 0, 2, 2。排序后，距离为 0, 2, 2。由于 02 + 22 = 22，节点 1 是特殊的。
//节点 2 的距离分别为 2, 0, 2。排序后，距离为 0, 2, 2。由于 02 + 22 = 22，节点 2 是特殊的。
//节点 3 的距离分别为 2, 2, 0。排序后，距离为 0, 2, 2。这也满足勾股定理条件。
//因此，节点 1、2 和 3 是特殊节点，答案为 3。
//
//示例 2：
//
//输入： n = 4, edges = [[0,1],[1,2],[2,3]], x = 0, y = 3, z = 2
//
//输出： 0
//
//解释：
//
//对于每个节点，我们计算它到节点 x = 0、y = 3 和 z = 2 的距离。
//
//节点 0 的距离为 0, 3, 2。排序后，距离为 0, 2, 3，不满足勾股定理条件。
//节点 1 的距离为 1, 2, 1。排序后，距离为 1, 1, 2，不满足勾股定理条件。
//节点 2 的距离为 2, 1, 0。排序后，距离为 0, 1, 2，不满足勾股定理条件。
//节点 3 的距离为 3, 0, 1. 排序后，距离为 0, 1, 3，不满足勾股定理条件。
//没有节点满足勾股定理条件。因此，答案为 0。
//
//示例 3：
//
//输入： n = 4, edges = [[0,1],[1,2],[1,3]], x = 1, y = 3, z = 0
//
//输出： 1
//
//解释：
//
//对于每个节点，我们计算它到节点 x = 1、y = 3 和 z = 0 的距离。
//
//节点 0 的距离为 1, 2, 0。排序后，距离为 0, 1, 2，不满足勾股定理条件。
//节点 1 的距离为 0, 1, 1。排序后，距离为 0, 1, 1。由于 02 + 12 = 12，节点 1 是特殊的。
//节点 2 的距离为 1, 2, 2。排序后，距离为 1, 2, 2，不满足勾股定理条件。
//节点 3 的距离为 1, 0, 2。排序后，距离为 0, 1, 2，不满足勾股定理条件。
//因此，答案为 1。
//
//
//
//提示：
//
//4 <= n <= 105
//edges.length == n - 1
//edges[i] = [ui, vi]
//0 <= ui, vi, x, y, z <= n - 1
//x, y 和 z 互不相同。
//输入生成的 edges 表示一棵有效的树。

// bfs计算最短距离
func specialNodes(n int, edges [][]int, x int, y int, z int) int {
	graph := make([][]int, n)
	for _, edge := range edges {
		from, to := edge[0], edge[1]
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	bfs := func(node int) []int {
		dis := make([]int, n)
		visit := make([]bool, n)
		visit[node] = true
		queue := []int{node}
		depth := 0
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				t := queue[i]
				for _, next := range graph[t] {
					if !visit[next] {
						visit[next] = true
						dis[next] = depth + 1
						queue = append(queue, next)
					}
				}
			}
			queue = queue[size:]
			depth++
		}
		return dis
	}
	cnt := 0
	dxArr, dyArr, dzArr := bfs(x), bfs(y), bfs(z)
	for i := 0; i < n; i++ {
		dx, dy, dz := dxArr[i], dyArr[i], dzArr[i]
		if dx*dx+dy*dy == dz*dz || dx*dx+dz*dz == dy*dy || dz*dz+dy*dy == dx*dx {
			cnt++
		}
	}
	return cnt
}
