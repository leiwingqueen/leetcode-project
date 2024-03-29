package wc369

// There exists an undirected tree rooted at node 0 with n nodes labeled from 0 to n - 1. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given a 0-indexed array coins of size n where coins[i] indicates the number of coins in the vertex i, and an integer k.
//
//Starting from the root, you have to collect all the coins such that the coins at a node can only be collected if the coins of its ancestors have been already collected.
//
//Coins at nodei can be collected in one of the following ways:
//
//Collect all the coins, but you will get coins[i] - k points. If coins[i] - k is negative then you will lose abs(coins[i] - k) points.
//Collect all the coins, but you will get floor(coins[i] / 2) points. If this way is used, then for all the nodej present in the subtree of nodei, coins[j] will get reduced to floor(coins[j] / 2).
//Return the maximum points you can get after collecting the coins from all the tree nodes.
//
//
//
//Example 1:
//
//
//Input: edges = [[0,1],[1,2],[2,3]], coins = [10,10,3,3], k = 5
//Output: 11
//Explanation:
//Collect all the coins from node 0 using the first way. Total points = 10 - 5 = 5.
//Collect all the coins from node 1 using the first way. Total points = 5 + (10 - 5) = 10.
//Collect all the coins from node 2 using the second way so coins left at node 3 will be floor(3 / 2) = 1. Total points = 10 + floor(3 / 2) = 11.
//Collect all the coins from node 3 using the second way. Total points = 11 + floor(1 / 2) = 11.
//It can be shown that the maximum points we can get after collecting coins from all the nodes is 11.
//Example 2:
//
//
//Input: edges = [[0,1],[0,2]], coins = [8,4,4], k = 0
//Output: 16
//Explanation:
//Coins will be collected from all the nodes using the first way. Therefore, total points = (8 - 0) + (4 - 0) + (4 - 0) = 16.
//
//
//Constraints:
//
//n == coins.length
//2 <= n <= 105
//0 <= coins[i] <= 104
//edges.length == n - 1
//0 <= edges[i][0], edges[i][1] < n
//0 <= k <= 104

// dfs，时间复杂度2^n，必然超时
func maximumPoints(edges [][]int, coins []int, k int) int {
	n := len(coins)
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	var dfs func(node int, degree int, parent int) int
	dfs = func(node int, degree int, parent int) int {
		// 方式1
		r1 := coins[node]/degree - k
		for _, next := range graph[node] {
			if next != parent {
				r1 += dfs(next, degree, node)
			}
		}
		// 方式2
		r2 := coins[node] / degree / 2
		for _, next := range graph[node] {
			if next != parent {
				r2 += dfs(next, degree*2, node)
			}
		}
		if r1 > r2 {
			return r1
		} else {
			return r2
		}
	}
	return dfs(0, 1, -1)
}

// 增加记忆，通过
func maximumPoints2(edges [][]int, coins []int, k int) int {
	n := len(coins)
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	mem := make(map[int64]int)
	var dfs func(node int, degree int, parent int) int
	dfs = func(node int, degree int, parent int) int {
		if degree > 10_000 {
			return 0
		}
		if v, ok := mem[(int64(node)<<32)|int64(degree)]; ok {
			return v
		}
		res := 0
		defer func() {
			mem[(int64(node)<<32)|int64(degree)] = res
		}()
		// 方式1
		r1 := coins[node]/degree - k
		for _, next := range graph[node] {
			if next != parent {
				r1 += dfs(next, degree, node)
			}
		}
		// 方式2
		r2 := coins[node] / degree / 2
		for _, next := range graph[node] {
			if next != parent {
				r2 += dfs(next, degree*2, node)
			}
		}
		if r1 > r2 {
			res = r1
		} else {
			res = r2
		}
		return res
	}
	return dfs(0, 1, -1)
}

// 记忆稍微优化一下
func maximumPoints3(edges [][]int, coins []int, k int) int {
	n := len(coins)
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	mem := make([][]int, n)
	for i := 0; i < n; i++ {
		mem[i] = make([]int, 14)
		for j := 0; j < 14; j++ {
			mem[i][j] = -1
		}
	}
	var dfs func(node int, degree int, parent int) int
	dfs = func(node int, degree int, parent int) int {
		if degree >= 14 {
			return 0
		}
		if mem[node][degree] >= 0 {
			return mem[node][degree]
		}
		res := 0
		defer func() {
			mem[node][degree] = res
		}()
		// 方式1
		r1 := (coins[node] >> degree) - k
		// 方式2
		r2 := coins[node] >> (degree + 1)
		for _, next := range graph[node] {
			if next != parent {
				r1 += dfs(next, degree, node)
				r2 += dfs(next, degree+1, node)
			}
		}
		if r1 > r2 {
			res = r1
		} else {
			res = r2
		}
		return res
	}
	return dfs(0, 0, -1)
}
