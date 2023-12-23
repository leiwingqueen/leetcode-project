package bwc120

import "sort"

// 给你一棵 n 个节点的 无向 树，节点编号为 0 到 n - 1 ，树的根节点在节点 0 处。同时给你一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。
//
//给你一个长度为 n 下标从 0 开始的整数数组 cost ，其中 cost[i] 是第 i 个节点的 开销 。
//
//你需要在树中每个节点都放置金币，在节点 i 处的金币数目计算方法如下：
//
//如果节点 i 对应的子树中的节点数目小于 3 ，那么放 1 个金币。
//否则，计算节点 i 对应的子树内 3 个不同节点的开销乘积的 最大值 ，并在节点 i 处放置对应数目的金币。如果最大乘积是 负数 ，那么放置 0 个金币。
//请你返回一个长度为 n 的数组 coin ，coin[i]是节点 i 处的金币数目。

func placedCoins(edges [][]int, cost []int) []int64 {
	n := len(edges) + 1
	graph := make([][]int, n)
	for _, edge := range edges {
		x, y := edge[0], edge[1]
		graph[x] = append(graph[x], y)
		graph[y] = append(graph[y], x)
	}
	res := make([]int64, n)
	// 返回3个最大和3个最小的负数
	var dfs func(node, parent int) ([]int, []int)
	dfs = func(node, parent int) ([]int, []int) {
		var mx1 []int
		var mx2 []int
		for _, next := range graph[node] {
			if parent != next {
				s1, s2 := dfs(next, node)
				for _, d := range s1 {
					if len(mx1) < 3 {
						mx1 = append(mx1, d)
						sort.Ints(mx1)
					} else if d > mx1[0] {
						mx1[0] = d
						sort.Ints(mx1)
					}
				}
				for _, d := range s2 {
					if len(mx2) < 3 {
						mx2 = append(mx2, d)
						sort.Ints(mx2)
					} else if d < mx2[2] {
						mx2[2] = d
						sort.Ints(mx2)
					}
				}
			}
		}
		if cost[node] > 0 {
			mx1 = append(mx1, cost[node])
			sort.Ints(mx1)
			if len(mx1) > 3 {
				mx1 = mx1[1:]
			}
		} else {
			mx2 = append(mx2, cost[node])
			sort.Ints(mx2)
			if len(mx2) > 3 {
				mx2 = mx2[:3]
			}
		}
		if len(mx1)+len(mx2) < 3 {
			res[node] = 1
		} else {
			res[node] = 0
			// 取三个最大的正数
			if len(mx1) >= 3 {
				res[node] = int64(mx1[0]) * int64(mx1[1]) * int64(mx1[2])
			}
			// 取两个最小的负数，然后加一个最大的正数
			if len(mx2) >= 2 && len(mx1) > 0 {
				r := int64(mx1[len(mx1)-1]) * int64(mx2[0]) * int64(mx2[1])
				if r > res[node] {
					res[node] = r
				}
			}
		}
		return mx1, mx2
	}
	dfs(0, -1)
	return res
}
