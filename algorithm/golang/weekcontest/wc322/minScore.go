package wc322

// 错误
func minScore(n int, roads [][]int) int {
	queue := []int{0}
	visit := make([]bool, n)
	visit[0] = true
	graph := make([][][]int, n)
	for _, road := range roads {
		from := road[0] - 1
		to := road[1] - 1
		graph[from] = append(graph[from], []int{to, road[2]})
		graph[to] = append(graph[to], []int{from, road[2]})
	}
	end := false
	res := -1
	for len(queue) > 0 {
		pop := queue[0]
		queue = queue[1:]
		for _, next := range graph[pop] {
			if next[0] == n-1 {
				end = true
			}
			if !visit[next[0]] {
				if res < 0 || next[1] < res {
					res = next[1]
				}
				queue = append(queue, next[0])
				visit[next[0]] = true
			}
		}
	}
	if !end {
		return -1
	} else {
		return res
	}
}

// 通过f
func minScore2(n int, roads [][]int) int {
	uf := Construct(n)
	for _, road := range roads {
		uf.union(road[0]-1, road[1]-1, road[2])
	}
	if uf.find(0) != uf.find(n-1) {
		return -1
	} else {
		return uf.distance[uf.find(0)]
	}
}

// 并查集模板
type UnionFind struct {
	parent   []int
	distance []int
}

func Construct(n int) *UnionFind {
	grid := make([]int, n)
	distance := make([]int, n)
	for i := 0; i < n; i++ {
		grid[i] = i
		distance[i] = -1
	}
	return &UnionFind{grid, distance}
}

func (uf *UnionFind) find(x int) int {
	for uf.parent[x] != uf.parent[uf.parent[x]] {
		//路径压缩
		uf.parent[x] = uf.parent[uf.parent[x]]
	}
	return uf.parent[x]
}

func (uf *UnionFind) union(x int, y int, d int) {
	rootX := uf.find(x)
	rootY := uf.find(y)
	if rootX != rootY {
		uf.parent[rootX] = rootY
	}
	dis := d
	if uf.distance[rootX] > 0 && uf.distance[rootX] < dis {
		dis = uf.distance[rootX]
	}
	if uf.distance[rootY] > 0 && uf.distance[rootY] < dis {
		dis = uf.distance[rootY]
	}
	uf.distance[rootY] = dis
}
