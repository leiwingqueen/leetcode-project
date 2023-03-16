package bwc99

import "sort"

// 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
//
//你需要将 ranges 分成 两个 组（可以为空），满足：
//
//每个区间只属于一个组。
//两个有 交集 的区间必须在 同一个 组内。
//如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
//
//比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
//请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//输入：ranges = [[6,10],[5,15]]
//输出：2
//解释：
//两个区间有交集，所以它们必须在同一个组内。
//所以有两种方案：
//- 将两个区间都放在第 1 个组中。
//- 将两个区间都放在第 2 个组中。
//示例 2：
//
//输入：ranges = [[1,3],[10,20],[2,5],[4,8]]
//输出：4
//解释：
//区间 [1,3] 和 [2,5] 有交集，所以它们必须在同一个组中。
//同理，区间 [2,5] 和 [4,8] 也有交集，所以它们也必须在同一个组中。
//所以总共有 4 种分组方案：
//- 所有区间都在第 1 组。
//- 所有区间都在第 2 组。
//- 区间 [1,3] ，[2,5] 和 [4,8] 在第 1 个组中，[10,20] 在第 2 个组中。
//- 区间 [1,3] ，[2,5] 和 [4,8] 在第 2 个组中，[10,20] 在第 1 个组中。
//
//
//提示：
//
//1 <= ranges.length <= 105
//ranges[i].length == 2
//0 <= starti <= endi <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 朴素解法
func countWays(ranges [][]int) int {
	mod := 1_000_000_007
	n := len(ranges)
	// 构建图，超时，O(n^2)
	graph := make([][]int, n)
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			l1, r1 := ranges[i][0], ranges[i][1]
			l2, r2 := ranges[j][0], ranges[j][1]
			if r1 >= l2 && r2 >= l1 {
				graph[i] = append(graph[i], j)
				graph[j] = append(graph[j], i)
			}
		}
	}
	res := 1
	visit := make([]bool, n)
	var dfs func(node int)
	dfs = func(node int) {
		for _, next := range graph[node] {
			if !visit[next] {
				visit[next] = true
				dfs(next)
			}
		}
	}
	for i := 0; i < n; i++ {
		if !visit[i] {
			visit[i] = true
			dfs(i)
			res = (res << 1) % mod
		}
	}
	return res
}

// 优化解法
func countWays2(ranges [][]int) int {
	mod := 1_000_000_007
	n := len(ranges)
	sort.Slice(ranges, func(i, j int) bool {
		return ranges[i][1] < ranges[j][1]
	})
	// 构建图，超时，O(n^2)
	graph := make([][]int, n)
	for i := 1; i < n; i++ {
		for j := i - 1; j >= 0; j-- {
			l1 := ranges[i][0]
			r2 := ranges[j][1]
			if l1 <= r2 {
				graph[i] = append(graph[i], j)
				graph[j] = append(graph[j], i)
			} else {
				break
			}
		}
	}
	res := 1
	visit := make([]bool, n)
	var dfs func(node int)
	dfs = func(node int) {
		for _, next := range graph[node] {
			if !visit[next] {
				visit[next] = true
				dfs(next)
			}
		}
	}
	for i := 0; i < n; i++ {
		if !visit[i] {
			visit[i] = true
			dfs(i)
			res = (res << 1) % mod
		}
	}
	return res
}
