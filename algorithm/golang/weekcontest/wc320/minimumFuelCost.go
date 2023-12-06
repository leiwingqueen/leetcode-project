package wc320

// 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
//
//每个城市里有一个代表，他们都要去首都参加一个会议。
//
//每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
//
//城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
//
//请你返回到达首都最少需要多少升汽油。
//
//
//
//示例 1：
//
//
//
//输入：roads = [[0,1],[0,2],[0,3]], seats = 5
//输出：3
//解释：
//- 代表 1 直接到达首都，消耗 1 升汽油。
//- 代表 2 直接到达首都，消耗 1 升汽油。
//- 代表 3 直接到达首都，消耗 1 升汽油。
//最少消耗 3 升汽油。
//示例 2：
//
//
//
//输入：roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
//输出：7
//解释：
//- 代表 2 到达城市 3 ，消耗 1 升汽油。
//- 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
//- 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
//- 代表 1 直接到达首都，消耗 1 升汽油。
//- 代表 5 直接到达首都，消耗 1 升汽油。
//- 代表 6 到达城市 4 ，消耗 1 升汽油。
//- 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
//最少消耗 7 升汽油。
//示例 3：
//
//
//
//输入：roads = [], seats = 1
//输出：0
//解释：没有代表需要从别的城市到达首都。
//
//
//提示：
//
//1 <= n <= 105
//roads.length == n - 1
//roads[i].length == 2
//0 <= ai, bi < n
//ai != bi
//roads 表示一棵合法的树。
//1 <= seats <= 105

func minimumFuelCost(roads [][]int, seats int) int64 {
	n := len(roads) + 1
	if n == 1 {
		return 0
	}
	graph := make([][]int, n)
	for _, road := range roads {
		from := road[0]
		to := road[1]
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	queue := []int{0}
	visit := make([]bool, n)
	visit[0] = true
	var res int64
	revert := make([]int, n)
	var leaf [][]int
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			childCnt := 0
			for _, next := range graph[queue[i]] {
				if !visit[next] {
					childCnt++
					queue = append(queue, next)
					visit[next] = true
					revert[next] = queue[i]
				}
			}
			if childCnt == 0 {
				leaf = append(leaf, []int{queue[i], depth})
			}
		}
		queue = queue[size:]
		depth++
	}
	nodeCnt := make([]int, n)
	for _, node := range leaf {
		curNode := node[0]
		curDepth := 0
		for curNode > 0 {
			if nodeCnt[curNode] == 0 {
				nodeCnt[curNode]++
			}
			nodeCnt[curNode] += curDepth
			curNode = revert[curNode]
			curDepth++
		}
	}
	// 这里还是有问题
	for i := 1; i < n; i++ {
		if nodeCnt[i]%seats == 0 {
			res += int64(nodeCnt[i] / seats)
		} else {
			res += int64(nodeCnt[i]/seats) + 1
		}
	}
	return res
}

// 这题贪心就像现实一样，一人开一辆车从自己城市出发。两车相遇，如果已经坐在车上的总人数小于一辆车座位数，那么所有人坐在一辆车上继续出发。
// 这个反而是理解了
// 典型的拓补排序
func minimumFuelCost2(roads [][]int, seats int) int64 {
	n := len(roads) + 1
	graph := make([][]int, n)
	for _, road := range roads {
		x, y := road[0], road[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	// dfs计算每个节点的子树
	var res int64
	var dfs func(node, parent int) int64
	dfs = func(node, parent int) int64 {
		var cnt int64
		cnt = 1
		for _, next := range graph[node] {
			if next != parent {
				cnt += dfs(next, node)
			}
		}
		if node != 0 {
			res += (cnt + int64(seats) - 1) / int64(seats)
		}
		return cnt
	}
	dfs(0, -1)
	return res
}
