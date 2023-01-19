package wc328

// 给你一个 n 个节点的无向无根图，节点编号为 0 到 n - 1 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。
//
//每个节点都有一个价值。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价值。
//
//一条路径的 价值和 是这条路径上所有节点的价值之和。
//
//你可以选择树中任意一个节点作为根节点 root 。选择 root 为根的 开销 是以 root 为起点的所有路径中，价值和 最大的一条路径与最小的一条路径的差值。
//
//请你返回所有节点作为根节点的选择中，最大 的 开销 为多少。
//
//
//
//示例 1：
//
//
//
//输入：n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
//输出：24
//解释：上图展示了以节点 2 为根的树。左图（红色的节点）是最大价值和路径，右图（蓝色的节点）是最小价值和路径。
//- 第一条路径节点为 [2,1,3,4]：价值为 [7,8,6,10] ，价值和为 31 。
//- 第二条路径节点为 [2] ，价值为 [7] 。
//最大路径和与最小路径和的差值为 24 。24 是所有方案中的最大开销。
//示例 2：
//
//
//
//输入：n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
//输出：2
//解释：上图展示了以节点 0 为根的树。左图（红色的节点）是最大价值和路径，右图（蓝色的节点）是最小价值和路径。
//- 第一条路径包含节点 [0,1,2]：价值为 [1,1,1] ，价值和为 3 。
//- 第二条路径节点为 [0] ，价值为 [1] 。
//最大路径和与最小路径和的差值为 2 。2 是所有方案中的最大开销。
//
//
//提示：
//
//1 <= n <= 105
//edges.length == n - 1
//0 <= ai, bi <= n - 1
//edges 表示一棵符合题面要求的树。
//price.length == n
//1 <= price[i] <= 105
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/difference-between-maximum-and-minimum-price-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxOutput(n int, edges [][]int, price []int) int64 {
	graph := make([][]int, n)
	for _, edge := range edges {
		x := edge[0]
		y := edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	var res int64
	var dfs func(cur int, parent int) (int64, int64)
	dfs = func(cur int, parent int) (int64, int64) {
		p := int64(price[cur])
		var mx1 int64
		var mx2 int64
		mx1 = int64(price[cur])
		mx2 = 0
		for _, next := range graph[cur] {
			if next != parent {
				s1, s2 := dfs(next, cur)
				mx := Max(mx2+s1, mx1+s2)
				if mx > res {
					res = mx
				}
				mx1 = Max(mx1, s1+p)
				mx2 = Max(mx2, s2+p)
			}
		}
		return mx1, mx2
	}
	x, y := dfs(0, -1)
	println(x, y)
	return res
}

func Max(a int64, b int64) int64 {
	if a > b {
		return a
	} else {
		return b
	}
}
