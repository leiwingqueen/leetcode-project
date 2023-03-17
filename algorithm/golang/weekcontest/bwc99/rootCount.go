package bwc99

import "fmt"

// Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。树用一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。
//
//Alice 想要 Bob 找到这棵树的根。她允许 Bob 对这棵树进行若干次 猜测 。每一次猜测，Bob 做如下事情：
//
//选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
//Bob 猜测树中 u 是 v 的 父节点 。
//Bob 的猜测用二维整数数组 guesses 表示，其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。
//
//Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。
//
//给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。如果没有这样的树，则返回 0。
//
//
//
//示例 1：
//
//
//
//输入：edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
//输出：3
//解释：
//根为节点 0 ，正确的猜测为 [1,3], [0,1], [2,4]
//根为节点 1 ，正确的猜测为 [1,3], [1,0], [2,4]
//根为节点 2 ，正确的猜测为 [1,3], [1,0], [2,4]
//根为节点 3 ，正确的猜测为 [1,0], [2,4]
//根为节点 4 ，正确的猜测为 [1,3], [1,0]
//节点 0 ，1 或 2 为根时，可以得到 3 个正确的猜测。
//示例 2：
//
//
//
//输入：edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k = 1
//输出：5
//解释：
//根为节点 0 ，正确的猜测为 [3,4]
//根为节点 1 ，正确的猜测为 [1,0], [3,4]
//根为节点 2 ，正确的猜测为 [1,0], [2,1], [3,4]
//根为节点 3 ，正确的猜测为 [1,0], [2,1], [3,2], [3,4]
//根为节点 4 ，正确的猜测为 [1,0], [2,1], [3,2]
//任何节点为根，都至少有 1 个正确的猜测。
//
//
//提示：
//
//edges.length == n - 1
//2 <= n <= 105
//1 <= guesses.length <= 105
//0 <= ai, bi, uj, vj <= n - 1
//ai != bi
//uj != vj
//edges 表示一棵有效的树。
//guesses[j] 是树中的一条边。
//guesses 是唯一的。
//0 <= k <= guesses.length
//通过
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-number-of-possible-root-nodes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 超时，假设有n个结点,m个猜测，则时间复杂度为O(n*(m+n))
func rootCount(edges [][]int, guesses [][]int, k int) int {
	// 构造图
	n := len(edges) + 1
	graph := make([][]int, n)
	for _, edge := range edges {
		from, to := edge[0], edge[1]
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	// 构造树，用一个数组表达每个节点的父节点，时间复杂度O(n)
	build := func(root int) []int {
		parent := make([]int, n)
		parent[root] = -1
		var dfs func(u int)
		dfs = func(u int) {
			for _, v := range graph[u] {
				if v != parent[u] {
					parent[v] = u
					dfs(v)
				}
			}
		}
		dfs(root)
		return parent
	}
	// 检查是否满足 k 个猜测
	check := func(parent []int) bool {
		cnt := 0
		for _, guess := range guesses {
			u, v := guess[0], guess[1]
			if parent[v] == u {
				cnt++
			}
			if cnt >= k {
				return true
			}
		}
		return false
	}
	res := 0
	for i := 0; i < n; i++ {
		if check(build(i)) {
			res++
		}
	}
	return res
}

// https://leetcode.cn/problems/count-number-of-possible-root-nodes/solution/huan-gen-dppythonjavacgo-by-endlesscheng-ccwy/
// 这个题解就很精妙
func rootCount2(edges [][]int, guesses [][]int, k int) int {
	// 构造图
	n := len(edges) + 1
	graph := make([][]int, n)
	for _, edge := range edges {
		from, to := edge[0], edge[1]
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	// 构造树，用一个数组表达每个节点的父节点，时间复杂度O(n)
	build := func(root int) []int {
		parent := make([]int, n)
		parent[root] = -1
		var dfs func(u int)
		dfs = func(u int) {
			for _, v := range graph[u] {
				if v != parent[u] {
					parent[v] = u
					dfs(v)
				}
			}
		}
		dfs(root)
		return parent
	}
	// 检查满足的猜测
	check := func(parent []int) int {
		cnt := 0
		for _, guess := range guesses {
			u, v := guess[0], guess[1]
			if parent[v] == u {
				cnt++
			}
		}
		return cnt
	}
	res := 0
	parent := build(0)
	cnt := check(parent)
	if cnt >= k {
		res++
	}
	guessMap := make(map[string]bool)
	for _, guess := range guesses {
		u, v := guess[0], guess[1]
		guessMap[fmt.Sprintf("%d,%d", u, v)] = true
	}
	queue := [][]int{{0, cnt}}
	visit := make([]bool, n)
	visit[0] = true
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			u := queue[0][0]
			c := queue[0][1]
			queue = queue[1:]
			for _, v := range graph[u] {
				if visit[v] {
					continue
				}
				visit[v] = true
				// 如果guesses中存在u,v。猜对次数减一
				// 如果guesses中存在v,u。猜对次数加一
				c2 := c
				if guessMap[fmt.Sprintf("%d,%d", u, v)] {
					c2--
				}
				if guessMap[fmt.Sprintf("%d,%d", v, u)] {
					c2++
				}
				if c2 >= k {
					res++
				}
				queue = append(queue, []int{v, c2})
			}
		}
	}
	return res
}
