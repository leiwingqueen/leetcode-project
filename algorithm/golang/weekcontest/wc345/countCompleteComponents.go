package wc345

func countCompleteComponents(n int, edges [][]int) int {
	uf := Construct(n)
	degree := make([]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		uf.union(x, y)
		degree[x]++
		degree[y]++
	}
	mp := make(map[int][]int)
	for i := 0; i < n; i++ {
		p := uf.find(i)
		mp[p] = append(mp[p], i)
	}
	res := 0
	for _, nodes := range mp {
		flag := true
		for _, node := range nodes {
			if degree[node] != len(nodes)-1 {
				flag = false
				break
			}
		}
		if flag {
			res++
		}
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
		uf.parent[rootX] = rootY
		uf.count--
	}
}

func (uf *UnionFind) getCount() int {
	return uf.count
}
