package wc318

import (
	"leetcode-go/util"
	"math"
	"sort"
)

// 贪心算法，不通过
func minimumTotalDistance(robot []int, factory [][]int) int64 {
	m := len(robot)
	n := len(factory)
	graph := make([][]int, m)
	for i := 0; i < m; i++ {
		graph[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			graph[i][j] = util.Abs(robot[i] - factory[j][0])
		}
	}
	var res int64
	mp1 := make(map[int]struct{})
	for i := 0; i < m; i++ {
		mp1[i] = struct{}{}
	}
	mp2 := make(map[int]struct{})
	for i := 0; i < n; i++ {
		mp2[i] = struct{}{}
	}
	for len(mp1) > 0 {
		dis := -1
		var r []int
		for i := range mp1 {
			for j := range mp2 {
				if dis < 0 || graph[i][j] < dis {
					r = []int{i, j}
					dis = graph[i][j]
				}
			}
		}
		// 更新节点
		res += int64(dis)
		delete(mp1, r[0])
		factory[r[1]][1]--
		if factory[r[1]][1] == 0 {
			delete(mp2, r[1])
		}
	}
	return res
}

func minimumTotalDistance2(robot []int, factory [][]int) int64 {
	sort.Ints(robot)
	sort.Slice(factory, func(i, j int) bool {
		return factory[i][0] <= factory[j][0]
	})
	m := len(robot)
	n := len(factory)
	dp := make([][]int64, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int64, m)
	}
	var sum int64
	for i := m - 1; i >= 0; i-- {
		sum += int64(util.Abs(robot[i] - factory[n-1][0]))
		if m-i <= factory[n-1][1] {
			dp[n-1][i] = sum
		} else {
			dp[n-1][i] = -1
		}
	}
	var cost func(i int, j int, k int) int64
	cost = func(i int, j int, k int) int64 {
		var res int64
		for l := 0; l < k; l++ {
			res += int64(util.Abs(robot[j+l] - factory[i][0]))
		}
		return res
	}
	for i := n - 2; i >= 0; i-- {
		for j := m - 1; j >= 0; j-- {
			var min int64 = math.MaxInt64
			for k := 0; j+k < m && k <= factory[i][1]; k++ {
				if dp[i+1][j+k] > 0 {
					sub := dp[i+1][j+k] + cost(i, j, k)
					if sub < min {
						min = sub
					}
				}
			}
			dp[i][j] = min
		}
	}
	return dp[0][0]
}
