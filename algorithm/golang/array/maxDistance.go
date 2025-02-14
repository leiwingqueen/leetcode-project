package array

import "sort"

// 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
//
//已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
//
//给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
//
//
//
//示例 1：
//
//
//
//输入：position = [1,2,3,4,7], m = 3
//输出：3
//解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
//示例 2：
//
//输入：position = [5,4,3,2,1,1000000000], m = 2
//输出：999999999
//解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
//
//
//提示：
//
//n == position.length
//2 <= n <= 10^5
//1 <= position[i] <= 10^9
//所有 position 中的整数 互不相同 。
//2 <= m <= position.length

func maxDistance(position []int, m int) int {
	n := len(position)
	sort.Ints(position)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, m)
	}
	// dp[n][m]=max(dp[n-1][m],min(dp[n-1][m-1],pos[n-1]-pos[n-2]))
	for i := 1; i < n; i++ {
		dp[i][1] = position[i-1] - position[0]
	}
	for i := 2; i < n; i++ {
		for j := 2; j <= i && j < m; j++ {
			if i > j {
				dp[i][j] = dp[i-1][j]
			}
			dp[i][j] = max(dp[i][j], min(dp[i-1][j-1], position[i-1]-position[i-2]))
		}
	}
	return dp[n-1][m-1]
}
