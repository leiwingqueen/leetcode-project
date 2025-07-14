package wc458

// 给你一个无向连通图，包含 n 个节点，节点编号从 0 到 n - 1，以及一个二维整数数组 edges，其中 edges[i] = [ui, vi, wi] 表示一条连接节点 ui 和节点 vi 的无向边，边权为 wi，另有一个整数 k。
//
//你可以从图中移除任意数量的边，使得最终的图中 最多 只包含 k 个连通分量。
//
//连通分量的 成本 定义为该分量中边权的 最大值 。如果一个连通分量没有边，则其代价为 0。
//
//请返回在移除这些边之后，在所有连通分量之中的 最大成本 的 最小可能值 。
//
//
//
//示例 1：
//
//输入： n = 5, edges = [[0,1,4],[1,2,3],[1,3,2],[3,4,6]], k = 2
//
//输出： 4
//
//解释：
//
//
//
//移除节点 3 和节点 4 之间的边（权值为 6）。
//最终的连通分量成本分别为 0 和 4，因此最大代价为 4。
//示例 2：
//
//输入： n = 4, edges = [[0,1,5],[1,2,5],[2,3,5]], k = 1
//
//输出： 5
//
//解释：
//
//
//
//无法移除任何边，因为只允许一个连通分量（k = 1），图必须保持完全连通。
//该连通分量的成本等于其最大边权，即 5。
//
//
//提示：
//
//1 <= n <= 5 * 104
//0 <= edges.length <= 105
//edges[i].length == 3
//0 <= ui, vi < n
//1 <= wi <= 106
//1 <= k <= n
//输入图是连通图。

// 这道题是一个典型的二分查找的题目
// 首先我们需要看到单调性，如果t越大，那么连通分量的数量就可以越少（删除的边越少）
func minCost(n int, edges [][]int, k int) int {
	maxWight := 0
	for _, edge := range edges {
		maxWight = max(maxWight, edge[2])
	}
	check := func(t int) bool {
		uf := Construct(n)
		for _, edge := range edges {
			from, to, w := edge[0], edge[1], edge[2]
			if w <= t {
				uf.union(from, to)
			}
		}
		return uf.getCount() <= k
	}
	l, r := 0, maxWight
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

// 并查集模板
type UnionFind struct {
	parent []int
	count  int
}

func Construct(n int) *UnionFind {
	grid := make([]int, n)
	for i := 0; i < n; i++ {
		grid[i] = i
	}
	return &UnionFind{grid, n}
}

func (uf *UnionFind) find(x int) int {
	for uf.parent[x] != uf.parent[uf.parent[x]] {
		//路径压缩
		uf.parent[x] = uf.parent[uf.parent[x]]
	}
	return uf.parent[x]
}

func (uf *UnionFind) union(x int, y int) {
	rootX := uf.find(x)
	rootY := uf.find(y)
	if rootX != rootY {
		uf.parent[rootX] = rootY
		uf.count--
	}
}

func (uf *UnionFind) getCount() int {
	return uf.count
}
