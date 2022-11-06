package wc318

import "leetcode-go/util"

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

// 其实问题相当于n个物品，m个背包
