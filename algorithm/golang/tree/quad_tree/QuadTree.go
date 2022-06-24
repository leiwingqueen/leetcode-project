package quad_tree

type Node struct {
	Val         bool
	IsLeaf      bool
	TopLeft     *Node
	TopRight    *Node
	BottomLeft  *Node
	BottomRight *Node
}

func construct(grid [][]int) *Node {
	//前缀和计算，用来加速判断某个区间是否全0/1
	n := len(grid)
	prefixSum := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		prefixSum[i] = make([]int, n+1)
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= n; j++ {
			prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + grid[i-1][j-1]
		}
	}
	var dfs func(x1 int, y1 int, x2 int, y2 int) *Node
	dfs = func(x1 int, y1 int, x2 int, y2 int) *Node {
		if x1 == x2 && y1 == y2 {
			return &Node{Val: grid[x1][y1] == 1, IsLeaf: true}
		}
		node := &Node{Val: false, IsLeaf: false}
		//全1/0
		sum := prefixSum[x2+1][y2+1] - prefixSum[x2+1][y1] - prefixSum[x1][y2+1] + prefixSum[x1][y1]
		if sum == 0 {
			node.Val = false
			node.IsLeaf = true
			return node
		}
		if sum == (x2-x1+1)*(y2-y1+1) {
			node.Val = true
			node.IsLeaf = true
			return node
		}
		//分4个区域
		midX := x1 + (x2-x1)/2
		midY := y1 + (y2-y1)/2
		node.TopLeft = dfs(x1, y1, midX, midY)
		node.TopRight = dfs(x1, midY+1, midX, y2)
		node.BottomLeft = dfs(midX+1, y1, x2, midY)
		node.BottomRight = dfs(midX+1, midY+1, x2, y2)
		return node
	}
	return dfs(0, 0, n-1, n-1)
}
