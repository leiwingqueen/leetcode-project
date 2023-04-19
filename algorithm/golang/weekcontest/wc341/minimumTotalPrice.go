package wc341

//现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中
//edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
//
// 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
//
// 给定路径的 价格总和 是该路径上所有节点的价格之和。
//
// 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何
//你喜欢的路径前往节点 endi 。
//
// 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
//
// 返回执行所有旅行的最小价格总和。
//
//
//
// 示例 1：
// 输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,
//1],[2,3]]
//输出：23
//解释：
//上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
//第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
//第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
//第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
//所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
//
// 示例 2：
// 输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
//输出：1
//解释：
//上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
//第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
//所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
//
//
//
//
// 提示：
//
//
// 1 <= n <= 50
// edges.length == n - 1
// 0 <= ai, bi <= n - 1
// edges 表示一棵有效的树
// price.length == n
// price[i] 是一个偶数
// 1 <= price[i] <= 1000
// 1 <= trips.length <= 100
// 0 <= starti, endi <= n - 1
//
//
// 👍 21 👎 0

// 超时
func minimumTotalPrice(n int, edges [][]int, price []int, trips [][]int) int {
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	mp := make(map[int]int)
	var dfs func(parent, from, to int) bool
	dfs = func(parent, from, to int) bool {
		if from == to {
			mp[from]++
			return true
		}
		for _, next := range graph[from] {
			if next != parent {
				find := dfs(from, next, to)
				if find {
					mp[from]++
					return true
				}
			}
		}
		return false
	}
	for _, trip := range trips {
		from, to := trip[0], trip[1]
		dfs(-1, from, to)
	}
	sum := 0
	for k, v := range mp {
		sum += price[k] * v
	}
	mx := 0
	var dfs2 func(choose []bool, idx, total int)
	dfs2 = func(choose []bool, idx, total int) {
		if idx == n {
			if total > mx {
				mx = total
			}
			return
		}
		flag := true
		for _, next := range graph[idx] {
			if choose[next] {
				flag = false
				break
			}
		}
		if flag {
			choose[idx] = true
			dfs2(choose, idx+1, total+price[idx]*mp[idx]/2)
			choose[idx] = false
		}
		dfs2(choose, idx+1, total)
	}
	dfs2(make([]bool, n), 0, 0)
	return sum - mx
}

// 优化
func minimumTotalPrice2(n int, edges [][]int, price []int, trips [][]int) int {
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	mp := make(map[int]int)
	var dfs func(parent, from, to int) bool
	dfs = func(parent, from, to int) bool {
		if from == to {
			mp[from]++
			return true
		}
		for _, next := range graph[from] {
			if next != parent {
				find := dfs(from, next, to)
				if find {
					mp[from]++
					return true
				}
			}
		}
		return false
	}
	for _, trip := range trips {
		from, to := trip[0], trip[1]
		dfs(-1, from, to)
	}
	sum := 0
	for k, v := range mp {
		sum += price[k] * v
	}
	// 对节点进行分层，计算每一层折扣后的价值和，结果就会变成一个0,1背包的问题
	var arr []int
	queue := []int{0}
	visit := make([]bool, n)
	visit[0] = true
	for len(queue) > 0 {
		size := len(queue)
		s := 0
		for i := 0; i < size; i++ {
			node := queue[i]
			s += price[node] * mp[node]
			for _, next := range graph[node] {
				if !visit[next] {
					queue = append(queue, next)
					visit[next] = true
				}
			}
		}
		arr = append(arr, s/2)
		queue = queue[size:]
	}
	dp := make([]int, len(arr)+1)
	dp[1] = arr[0]
	for i := 2; i <= len(arr); i++ {
		dp[i] = dp[i-1]
		if dp[i-2]+arr[i-1] > dp[i] {
			dp[i] = dp[i-2] + arr[i-1]
		}
	}
	return sum - dp[len(arr)]
}

// 勉强通过
func minimumTotalPrice3(n int, edges [][]int, price []int, trips [][]int) int {
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	mp := make(map[int]int)
	var dfs func(parent, from, to int) bool
	dfs = func(parent, from, to int) bool {
		if from == to {
			mp[from]++
			return true
		}
		for _, next := range graph[from] {
			if next != parent {
				find := dfs(from, next, to)
				if find {
					mp[from]++
					return true
				}
			}
		}
		return false
	}
	for _, trip := range trips {
		from, to := trip[0], trip[1]
		dfs(-1, from, to)
	}
	sum := 0
	for k, v := range mp {
		sum += price[k] * v
	}
	// 增加cache，总算勉强通过了
	cache := make([][]int, n)
	for i := 0; i < n; i++ {
		cache[i] = []int{-1, -1}
	}
	var dfs2 func(idx int, parentChoose int, parentNode int) int
	dfs2 = func(idx int, parentChoose int, parentNode int) int {
		if cache[idx][parentChoose] >= 0 {
			return cache[idx][parentChoose]
		}
		// 不选
		r1 := 0
		for _, next := range graph[idx] {
			if next != parentNode {
				s1 := dfs2(next, 0, idx)
				r1 += s1
			}
		}
		if parentChoose == 0 {
			r2 := 0
			for _, next := range graph[idx] {
				if next != parentNode {
					s2 := dfs2(next, 1, idx)
					r2 += s2
				}
			}
			r2 += price[idx] * mp[idx] / 2
			if r2 > r1 {
				r1 = r2
			}
		}
		cache[idx][parentChoose] = r1
		return r1
	}
	mx := dfs2(0, 0, -1)
	return sum - mx
}
