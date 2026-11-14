package wc527

// 给你一个整数 n，表示一棵有 n 个节点的树，节点编号从 0 到 n - 1，并且树以节点 0 为 根节点。这棵树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 之间存在一条 无向 边。
//
//另给你一个长度为 n 的字符串 colors，其中 colors[i] 表示节点 i 的颜色：'R' 表示红色，'B' 表示蓝色。
//
//你可以 翻转 任意节点的颜色：从 'R' 变为 'B'，或从 'B' 变为 'R'。翻转节点 u 的代价等于以节点 u 为根的子树的大小。
//
//你的目标是通过翻转颜色，使所有 红色 节点在树中形成 一个连通分量 。蓝色节点可以不连通。没有红色节点的配置也视为有效。
//
//返回实现这一目标所需的 最小总代价 。
//
//
//
//示例 1：
//
//输入： n = 3, colors = "RBR", edges = [[0,1],[1,2]]
//
//输出： 1
//
//解释：
//
//
//
//有两个红色节点（0 和 2），它们彼此不连通。
//可以将节点 2 从 'R'（红色）翻转为 'B'（蓝色）。节点 2 的子树大小为 1，因此代价为 1。
//翻转后，只有节点 0 仍为红色，恰好形成一个红色连通分量。
//示例 2：
//
//输入： n = 3, colors = "RBR", edges = [[0,1],[0,2]]
//
//输出： 0
//
//解释：
//
//
//
//有两个红色节点（0 和 2），它们已经彼此连通。
//所有红色节点已经形成一个连通分量，因此无须翻转。最小总代价为 0。
//示例 3：
//
//输入： n = 4, colors = "BRRR", edges = [[0,1],[0,2],[0,3]]
//
//输出： 2
//
//解释：​​​​​​​
//
//
//
//有三个红色节点（1、2 和 3），它们彼此不连通。
//这三个红色节点都是叶节点，且互不连通。
//为了得到一个红色连通分量，保留一个红色叶节点，并将另外两个翻转为蓝色。
//每个叶节点的子树大小均为 1，因此翻转其中任意两个的总代价为 2。
//示例 4：
//
//输入： n = 3, colors = "BBB", edges = [[0,1],[1,2]]
//
//输出： 0
//
//解释：
//
//​​​​​​​
//
//所有节点均为蓝色，因此没有红色节点。
//空的红色节点集合也视为一个连通分量，因此当前状态已经有效，代价为 0。
//
//
//提示：​​​​​​​
//
//1 <= n <= 105
//n == colors.length
//colors[i] 为 'R' 或 'B'。
//edges.length == n - 1
//edges[i] = [ui, vi]
//0 <= ui, vi <= n - 1
//输入保证 edges 表示一棵有效的树。

func minCost(n int, colors string, edges [][]int) int64 {
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	// dfs计算每个节点的子树大小
	size := make([]int, n)
	var dfs func(node int, parent int) int
	dfs = func(node int, parent int) int {
		total := 1
		for _, child := range graph[node] {
			if child != parent {
				total += dfs(child, node)
			}
		}
		size[node] = total
		return total
	}
	dfs(0, -1)
	// 计算全部变成蓝色的成本
	var base int64
	for i := 0; i < n; i++ {
		if colors[i] == 'R' {
			base += int64(size[i])
		}
	}
	// dfs计算以某个节点为root的反转成本，如果原来是蓝色，成本是size[x],如果原来是红色,成本为-size[x]
	var maxDiff int64
	var dfs2 func(node, parent int) int64
	dfs2 = func(node, parent int) int64 {
		var cost int64
		if colors[node] == 'B' {
			cost -= int64(size[node])
		} else {
			cost += int64(size[node])
		}
		for _, child := range graph[node] {
			if child != parent {
				c := dfs2(child, node)
				if c >= 0 {
					cost += c
				}
			}
		}
		maxDiff = max(maxDiff, cost)
		return cost
	}
	dfs2(0, -1)
	return base - maxDiff
}
