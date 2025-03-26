package wc442

// 给你一个二维整数数组 properties，其维度为 n x m，以及一个整数 k。
//
//定义一个函数 intersect(a, b)，它返回数组 a 和 b 中 共有的不同整数的数量 。
//
//构造一个 无向图，其中每个索引 i 对应 properties[i]。如果且仅当 intersect(properties[i], properties[j]) >= k（其中 i 和 j 的范围为 [0, n - 1] 且 i != j），节点 i 和节点 j 之间有一条边。
//
//返回结果图中 连通分量 的数量。
//
//
//
//示例 1：
//
//输入： properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1
//
//输出： 3
//
//解释：
//
//生成的图有 3 个连通分量：
//
//
//
//示例 2：
//
//输入： properties = [[1,2,3],[2,3,4],[4,3,5]], k = 2
//
//输出： 1
//
//解释：
//
//生成的图有 1 个连通分量：
//
//
//
//示例 3：
//
//输入： properties = [[1,1],[1,1]], k = 2
//
//输出： 2
//
//解释：
//
//intersect(properties[0], properties[1]) = 1，小于 k。因此在图中 properties[0] 和 properties[1] 之间没有边。
//
//
//
//提示：
//
//1 <= n == properties.length <= 100
//1 <= m == properties[i].length <= 100
//1 <= properties[i][j] <= 100
//1 <= k <= m

func numberOfComponents(properties [][]int, k int) int {
	n := len(properties)
	var edges [][]int
	mp := make([]map[int]bool, n)
	for i, property := range properties {
		mp[i] = make(map[int]bool)
		for _, num := range property {
			mp[i][num] = true
		}
	}
	for i := 0; i < n; i++ {
		p1 := mp[i]
		for j := i + 1; j < n; j++ {
			p2 := mp[j]
			cnt := 0
			for num := range p1 {
				if p2[num] {
					cnt++
				}
			}
			if cnt >= k {
				edges = append(edges, []int{i, j})
			}
		}
	}
	// 计算连通分量
	uf := Construct(n)
	for _, edge := range edges {
		uf.union(edge[0], edge[1])
	}
	return uf.count
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
