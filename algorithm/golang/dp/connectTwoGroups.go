package dp

import "math"

//给你两组点，其中第一组中有 size1 个点，第二组中有 size2 个点，且 size1 >= size2 。
//
// 任意两点间的连接成本 cost 由大小为 size1 x size2 矩阵给出，其中 cost[i][j] 是第一组中的点 i 和第二组中的点 j 的连接
//成本。如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至
//少与第一组中的一个点连接。
//
// 返回连通两组点所需的最小成本。
//
//
//
// 示例 1：
//
//
//
// 输入：cost = [[15, 96], [36, 2]]
//输出：17
//解释：连通两组点的最佳方法是：
//1--A
//2--B
//总成本为 17 。
//
//
// 示例 2：
//
//
//
// 输入：cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
//输出：4
//解释：连通两组点的最佳方法是：
//1--A
//2--B
//2--C
//3--A
//最小成本为 4 。
//请注意，虽然有多个点连接到第一组中的点 2 和第二组中的点 A ，但由于题目并不限制连接点的数目，所以只需要关心最低总成本。
//
// 示例 3：
//
// 输入：cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
//输出：10
//
//
//
//
// 提示：
//
//
// size1 == cost.length
// size2 == cost[i].length
// 1 <= size1, size2 <= 12
// size1 >= size2
// 0 <= cost[i][j] <= 100
//
//
// Related Topics 位运算 数组 动态规划 状态压缩 矩阵 👍 108 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

// 先写个基本的回溯，超时，剩下只需要增加一些记忆
func connectTwoGroups(cost [][]int) int {
	m, n := len(cost), len(cost[0])
	var dfs func(state int) int
	dfs = func(state int) int {
		if state == 0 {
			return 0
		}
		if state&((1<<m)-1) != 0 {
			// 第一组还有没选的,遍历第一组
			for i := 0; i < m; i++ {
				// 选择
				if state&(1<<i) != 0 {
					// 选择第二组的
					res := math.MaxInt32
					for j := 0; j < n; j++ {
						nState := state ^ (1 << i)
						if state&(1<<(j+m)) != 0 {
							nState ^= 1 << (j + m)
						}
						sub := dfs(nState) + cost[i][j]
						if sub < res {
							res = sub
						}
					}
					return res
				}
			}
		} else {
			// 第一组的都已经被选完了，直接选择第二组
			for i := 0; i < n; i++ {
				if state&(1<<(m+i)) != 0 {
					// 这里可以直接用贪心了
					c := 0
					for j := 0; j < m; j++ {
						if cost[j][i] < cost[c][i] {
							c = j
						}
					}
					return dfs(state^(1<<(m+i))) + cost[c][i]
				}
			}
		}
		// never reach
		return -1
	}
	return dfs((1 << (m + n)) - 1)
}

// 在上面的基础上增加记忆，击败10%的用户
// 这里基本等价于穷举，时间复杂度O(2^(m+n))，理论上是不能通过的，但是勉强通过了
func connectTwoGroups2(cost [][]int) int {
	m, n := len(cost), len(cost[0])
	mem := make(map[int]int)
	var dfs func(state int) int
	dfs = func(state int) int {
		if state == 0 {
			return 0
		}
		if v, exist := mem[state]; exist {
			return v
		}
		if state&((1<<m)-1) != 0 {
			// 第一组还有没选的,遍历第一组
			for i := 0; i < m; i++ {
				// 选择
				if state&(1<<i) != 0 {
					// 选择第二组的
					res := math.MaxInt32
					for j := 0; j < n; j++ {
						nState := state ^ (1 << i)
						if state&(1<<(j+m)) != 0 {
							nState ^= 1 << (j + m)
						}
						sub := dfs(nState) + cost[i][j]
						if sub < res {
							res = sub
						}
					}
					mem[state] = res
					return res
				}
			}
		} else {
			// 第一组的都已经被选完了，直接选择第二组
			for i := 0; i < n; i++ {
				if state&(1<<(m+i)) != 0 {
					// 这里可以直接用贪心了
					c := 0
					for j := 0; j < m; j++ {
						if cost[j][i] < cost[c][i] {
							c = j
						}
					}
					res := dfs(state^(1<<(m+i))) + cost[c][i]
					mem[state] = res
					return res
				}
			}
		}
		// never reach
		return -1
	}
	return dfs((1 << (m + n)) - 1)
}

//leetcode submit region end(Prohibit modification and deletion)
