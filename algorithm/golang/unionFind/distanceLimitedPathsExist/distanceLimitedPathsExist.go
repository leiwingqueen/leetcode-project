package distanceLimitedPathsExist

import "sort"

// 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
//
//给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
//
//请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
//
//
//
//示例 1：
//
//
//输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
//输出：[false,true]
//解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
//对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
//对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
//示例 2：
//
//
//输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
//输出：[true,false]
//解释：上图为给定数据。
//
//
//提示：
//
//2 <= n <= 105
//1 <= edgeList.length, queries.length <= 105
//edgeList[i].length == 3
//queries[j].length == 3
//0 <= ui, vi, pj, qj <= n - 1
//ui != vi
//pj != qj
//1 <= disi, limitj <= 109
//两个点之间可能有 多条 边。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func distanceLimitedPathsExist(n int, edgeList [][]int, queries [][]int) []bool {
	sort.Slice(edgeList, func(i, j int) bool {
		return edgeList[i][2] <= edgeList[j][2]
	})
	k := len(queries)
	queries2 := make([]int, k)
	for i := 0; i < k; i++ {
		queries2[i] = i
	}
	sort.Slice(queries2, func(i, j int) bool {
		q1 := queries[queries2[i]]
		q2 := queries[queries2[j]]
		return q1[2] <= q2[2]
	})
	uf := Construct(n)
	l := 0
	res := make([]bool, k)
	for i := 0; i < k; i++ {
		idx := queries2[i]
		for l < len(edgeList) && edgeList[l][2] < queries[idx][2] {
			uf.union(edgeList[l][0], edgeList[l][1])
			l++
		}
		res[idx] = uf.find(queries[idx][0]) == uf.find(queries[idx][1])
	}
	return res
}

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
