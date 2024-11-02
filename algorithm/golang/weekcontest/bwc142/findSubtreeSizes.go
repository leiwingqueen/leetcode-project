package bwc142

// 给你一棵 n 个节点且根节点为编号 0 的树，节点编号为 0 到 n - 1 。这棵树用一个长度为 n 的数组 parent 表示，其中 parent[i] 是第 i 个节点的父亲节点的编号。由于节点 0 是根，parent[0] == -1 。
//
//给你一个长度为 n 的字符串 s ，其中 s[i] 是节点 i 对应的字符。
//
//对于节点编号从 1 到 n - 1 的每个节点 x ，我们 同时 执行以下操作 一次 ：
//
//找到距离节点 x 最近 的祖先节点 y ，且 s[x] == s[y] 。
//如果节点 y 不存在，那么不做任何修改。
//否则，将节点 x 与它父亲节点之间的边 删除 ，在 x 与 y 之间连接一条边，使 y 变为 x 新的父节点。
//请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是 最终 树中，节点 i 为根的
//子树
// 的 大小 。
//
//
//
//示例 1：
//
//输入：parent = [-1,0,0,1,1,1], s = "abaabc"
//
//输出：[6,3,1,1,1,1]
//
//解释：
//
//
//
//节点 3 的父节点从节点 1 变为节点 0 。
//
//示例 2：
//
//输入：parent = [-1,0,4,0,1], s = "abbba"
//
//输出：[5,2,1,1,1]
//
//解释：
//
//
//
//以下变化会同时发生：
//
//节点 4 的父节点从节点 1 变为节点 0 。
//节点 2 的父节点从节点 4 变为节点 1 。
//
//
//提示：
//
//n == parent.length == s.length
//1 <= n <= 105
//对于所有的 i >= 1 ，都有 0 <= parent[i] <= n - 1 。
//parent[0] == -1
//parent 表示一棵合法的树。
//s 只包含小写英文字母。

func findSubtreeSizes(parent []int, s string) []int {
	n := len(parent)
	graph := make([][]int, n)
	for i := 0; i < n; i++ {
		j := parent[i]
		if j >= 0 {
			graph[i] = append(graph[i], j)
			graph[j] = append(graph[j], i)
		}
	}
	// 重新构造树的结构
	visit := make([]bool, n)
	var dfs func(last []int, node int, p int)
	dfs = func(last []int, node int, p int) {
		w := s[node]
		// 查看最后出现的祖先的下标
		if last[w-'a'] >= 0 {
			// 有祖先，更新父节点
			parent[node] = last[w-'a']
		}
		tmp := last[w-'a']
		last[w-'a'] = node
		// 继续递归后续的结点
		for _, next := range graph[node] {
			if next != p && !visit[next] {
				visit[next] = true
				dfs(last, next, node)
			}
		}
		// 还原
		last[w-'a'] = tmp
	}
	last := make([]int, 26)
	for i := 0; i < 26; i++ {
		last[i] = -1
	}
	dfs(last, 0, -1)
	// 然后遍历树的大小
	for i := 0; i < n; i++ {
		graph[i] = graph[i][:0]
	}
	for i := 0; i < n; i++ {
		j := parent[i]
		if j >= 0 {
			graph[i] = append(graph[i], j)
			graph[j] = append(graph[j], i)
		}
	}
	res := make([]int, n)
	var dfs2 func(node int, p int) int
	dfs2 = func(node int, p int) int {
		cnt := 1
		for _, next := range graph[node] {
			if next != p {
				cnt += dfs2(next, node)
			}
		}
		res[node] = cnt
		return cnt
	}
	dfs2(0, -1)
	return res
}
