package tree

// n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
//
//路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
//
//今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
//
//请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
//
//题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
//
//
//
//示例 1：
//
//
//
//输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
//输出：3
//解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
//示例 2：
//
//
//
//输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
//输出：2
//解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
//示例 3：
//
//输入：n = 3, connections = [[1,0],[2,0]]
//输出：0
//
//
//提示：
//
//2 <= n <= 5 * 10^4
//connections.length == n-1
//connections[i].length == 2
//0 <= connections[i][0], connections[i][1] <= n-1
//connections[i][0] != connections[i][1]

func minReorder(n int, connections [][]int) int {
	graph := make([][]int, n)
	dir := make(map[int64]bool)
	for _, con := range connections {
		x, y := con[0], con[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
		dir[int64(x<<32)|int64(y)] = true
	}
	res := 0
	var dfs func(node, parent int)
	dfs = func(node, parent int) {
		for _, next := range graph[node] {
			if next != parent {
				if !dir[int64(next)<<32|int64(node)] {
					res++
				}
				dfs(next, node)
			}
		}
	}
	dfs(0, -1)
	return res
}
