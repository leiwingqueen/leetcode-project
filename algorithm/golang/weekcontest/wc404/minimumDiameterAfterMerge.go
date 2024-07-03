package wc404

// 给你两棵 无向 树，分别有 n 和 m 个节点，节点编号分别为 0 到 n - 1 和 0 到 m - 1 。给你两个二维整数数组 edges1 和 edges2 ，长度分别为 n - 1 和 m - 1 ，其中 edges1[i] = [ai, bi] 表示在第一棵树中节点 ai 和 bi 之间有一条边，edges2[i] = [ui, vi] 表示在第二棵树中节点 ui 和 vi 之间有一条边。
//
//你必须在第一棵树和第二棵树中分别选一个节点，并用一条边连接它们。
//
//请你返回添加边后得到的树中，最小直径 为多少。
//
//一棵树的 直径 指的是树中任意两个节点之间的最长路径长度。
//
//
//
//示例 1：
//
//输入：edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
//
//输出：3
//
//解释：
//
//将第一棵树中的节点 0 与第二棵树中的任意节点连接，得到一棵直径为 3 得树。
//
//示例 2：
//
//输入：edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
//
//输出：5
//
//解释：
//
//将第一棵树中的节点 0 和第二棵树中的节点 0 连接，可以得到一棵直径为 5 的树。
//
//
//
//提示：
//
//1 <= n, m <= 105
//edges1.length == n - 1
//edges2.length == m - 1
//edges1[i].length == edges2[i].length == 2
//edges1[i] = [ai, bi]
//0 <= ai, bi < n
//edges2[i] = [ui, vi]
//0 <= ui, vi < m
//输入保证 edges1 和 edges2 分别表示一棵合法的树。

func minimumDiameterAfterMerge(edges1 [][]int, edges2 [][]int) int {
	// 分别计算中心点
	// A--B--C--D,偶数个点 (n-1)*2+1=2*n-1
	// A--B--C，奇数个点(n-1)*2
	cal := func(edges [][]int) int {
		n := len(edges) + 1
		graph := make([][]int, n)
		for _, edge := range edges {
			x, y := edge[0], edge[1]
			graph[x] = append(graph[x], y)
			graph[y] = append(graph[y], x)
		}
		if n == 1 {
			return 0
		}
		degree := make([]int, n)
		var leafNode []int
		for i, next := range graph {
			degree[i] = len(next)
			if degree[i] == 1 {
				leafNode = append(leafNode, i)
			}
		}
		depth := 0
		for len(leafNode) > 1 {
			size := len(leafNode)
			for _, node := range leafNode[:size] {
				degree[node]--
				for _, next := range graph[node] {
					degree[next]--
					if degree[next] == 1 {
						leafNode = append(leafNode, next)
					}
				}
			}
			leafNode = leafNode[size:]
			depth++
		}
		res := 2 * depth
		if len(leafNode) == 0 {
			res--
		}
		return res
	}
	d1 := cal(edges1)
	d2 := cal(edges2)
	return max(d1, d2, (d1+1)/2+(d2+1)/2+1)
}
