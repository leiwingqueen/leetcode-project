package tree

// 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
//
//请你返回树中的 合法路径数目 。
//
//如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
//
//注意：
//
//路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
//路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
//
//
//示例 1：
//
//
//
//输入：n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
//输出：4
//解释：恰好有一个质数编号的节点路径有：
//- (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
//- (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
//- (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
//- (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
//只有 4 条合法路径。
//示例 2：
//
//
//
//输入：n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
//输出：6
//解释：恰好有一个质数编号的节点路径有：
//- (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
//- (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
//- (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
//- (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
//- (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
//- (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
//只有 6 条合法路径。
//
//
//提示：
//
//1 <= n <= 105
//edges.length == n - 1
//edges[i].length == 2
//1 <= ui, vi <= n
//输入保证 edges 形成一棵合法的树。

func countPaths(n int, edges [][]int) int64 {
	// 埃氏筛
	isPrime := make([]bool, n+1)
	for i := 2; i <= n; i++ {
		isPrime[i] = true
	}
	for i := 2; i*i <= n; i++ {
		if isPrime[i] {
			for j := i * i; j <= n; j += i {
				isPrime[j] = false
			}
		}
	}
	// 构造树
	graph := make([][]int, n+1)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	// 节点i的不为质数的子树的大小
	dp := make([]int, n+1)
	var dfs func(node int, parent int, path *[]int)
	dfs = func(node int, parent int, path *[]int) {
		if isPrime[node] || dp[node] > 0 {
			return
		}
		*path = append(*path, node)
		for _, next := range graph[node] {
			if next != parent && !isPrime[next] {
				dfs(next, node, path)
			}
		}
	}
	for i := 1; i <= n; i++ {
		var path []int
		dfs(i, 0, &path)
		size := len(path)
		for _, j := range path {
			dp[j] = size
		}
	}
	// 最后再统计所有质数的点
	var res int64
	for i := 1; i <= n; i++ {
		if isPrime[i] {
			var sum int64
			for _, next := range graph[i] {
				res += int64(dp[next])
				res += sum * int64(dp[next])
				sum += int64(dp[next])
			}
		}
	}
	return res
}
