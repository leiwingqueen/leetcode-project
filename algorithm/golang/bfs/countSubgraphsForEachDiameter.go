package bfs

// 给你 n 个城市，编号为从 1 到 n 。同时给你一个大小为 n-1 的数组 edges ，其中 edges[i] = [ui, vi] 表示城市 ui 和 vi 之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 树 。
//
//一棵 子树 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。
//
//对于 d 从 1 到 n-1 ，请你找到城市间 最大距离 恰好为 d 的所有子树数目。
//
//请你返回一个大小为 n-1 的数组，其中第 d 个元素（下标从 1 开始）是城市间 最大距离 恰好等于 d 的子树数目。
//
//请注意，两个城市间距离定义为它们之间需要经过的边的数目。
//
//
//
//示例 1：
//
//
//
//输入：n = 4, edges = [[1,2],[2,3],[2,4]]
//输出：[3,4,0]
//解释：
//子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。
//子树 {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。
//不存在城市间最大距离为 3 的子树。
//示例 2：
//
//输入：n = 2, edges = [[1,2]]
//输出：[1]
//示例 3：
//
//输入：n = 3, edges = [[1,2],[2,3]]
//输出：[2,1]
//
//
//提示：
//
//2 <= n <= 15
//edges.length == n-1
//edges[i].length == 2
//1 <= ui, vi <= n
//题目保证 (ui, vi) 所表示的边互不相同。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 居然通过了，很艰难地通过了
func countSubgraphsForEachDiameter(n int, edges [][]int) []int {
	graph := make([][]int, n)
	for _, edge := range edges {
		from, to := edge[0], edge[1]
		graph[from-1] = append(graph[from-1], to-1)
		graph[to-1] = append(graph[to-1], from-1)
	}
	// 计算任意两个节点的距离
	dis := make([][]int, n)
	for i := 0; i < n; i++ {
		dis[i] = make([]int, n)
	}
	calDis := func(cur int) {
		depth := 0
		queue := []int{cur}
		visit := make([]bool, n)
		visit[cur] = true
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				node := queue[i]
				for _, next := range graph[node] {
					if !(visit[next]) {
						visit[next] = true
						dis[cur][next] = depth + 1
						queue = append(queue, next)
					}
				}
			}
			queue = queue[size:]
			depth++
		}
	}
	for i := 0; i < n; i++ {
		calDis(i)
	}
	// 计算一个图的最大距离
	calDis2 := func(node int) int {
		r := 0
		for i := 0; i < n; i++ {
			if node&(1<<i) == 0 {
				continue
			}
			for j := 0; j < n; j++ {
				if node&(1<<j) == 0 {
					continue
				}
				if dis[i][j] > r {
					r = dis[i][j]
				}
			}
		}
		return r
	}
	queue := make([]int, 0)
	visit := make(map[int]bool)
	for i := 0; i < n; i++ {
		queue = append(queue, 1<<i)
		visit[1<<i] = true
	}
	res := make([]int, n-1)
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			node := queue[i]
			for j := 0; j < n; j++ {
				if node&(1<<j) != 0 {
					// 遍历j的周围的节点，尝试增加一个节点
					for _, next := range graph[j] {
						nextNode := node | (1 << next)
						if !visit[nextNode] {
							queue = append(queue, nextNode)
							visit[nextNode] = true
							res[calDis2(nextNode)-1]++
						}
					}
				}
			}
		}
		queue = queue[size:]
	}
	return res
}
