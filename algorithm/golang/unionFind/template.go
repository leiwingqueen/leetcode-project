package unionFind

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
	for uf.parent[x] != x {
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
