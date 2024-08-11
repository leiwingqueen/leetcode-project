package wc409

// 给你一个整数 n 和一个二维整数数组 queries。
//
//有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
//
//queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，你需要找到从城市 0 到城市 n - 1 的最短路径的长度。
//
//所有查询中不会存在两个查询都满足 queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]。
//
//返回一个数组 answer，对于范围 [0, queries.length - 1] 中的每个 i，answer[i] 是处理完前 i + 1 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
//
//
//
//示例 1：
//
//输入： n = 5, queries = [[2, 4], [0, 2], [0, 4]]
//
//输出： [3, 2, 1]
//
//解释：
//
//
//
//新增一条从 2 到 4 的道路后，从 0 到 4 的最短路径长度为 3。
//
//
//
//新增一条从 0 到 2 的道路后，从 0 到 4 的最短路径长度为 2。
//
//
//
//新增一条从 0 到 4 的道路后，从 0 到 4 的最短路径长度为 1。
//
//示例 2：
//
//输入： n = 4, queries = [[0, 3], [0, 2]]
//
//输出： [1, 1]
//
//解释：
//
//
//
//新增一条从 0 到 3 的道路后，从 0 到 3 的最短路径长度为 1。
//
//
//
//新增一条从 0 到 2 的道路后，从 0 到 3 的最短路径长度仍为 1。
//
//
//
//提示:
//
//3 <= n <= 105
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= queries[i][0] < queries[i][1] < n
//1 < queries[i][1] - queries[i][0]
//查询中不存在重复的道路。
//不存在两个查询都满足 i != j 且 queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]。

func shortestDistanceAfterQueries3(n int, queries [][]int) []int {
	uf := Construct(n - 1)
	res := make([]int, len(queries))
	for i := range queries {
		x, y := queries[i][0], queries[i][1]
		// [x,y-1]的边都合并
		for j := x; j < y-1; j++ {
			uf.union(j, j+1)
		}
		res[i] = uf.count
	}
	return res
}

// 终于过了
func shortestDistanceAfterQueries4(n int, queries [][]int) []int {
	uf := Construct(n - 1)
	res := make([]int, len(queries))
	for i := range queries {
		x, y := queries[i][0], queries[i][1]
		j := y - 1
		for j >= x {
			r1 := uf.find(j)
			if r1 <= x {
				break
			}
			uf.union(r1, x)
			j = r1 - 1
		}
		res[i] = uf.count
	}
	return res
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
		// 这里选择更小的节点当root
		if rootX > rootY {
			rootX, rootY = rootY, rootX
		}
		uf.parent[rootY] = rootX
		uf.count--
	}
}

func (uf *UnionFind) getCount() int {
	return uf.count
}
