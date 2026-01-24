package wc485

import "sort"

// 假设f1(n,b)是选择一台机器<budget的最大值
// f2(n,b)是选择两台机器的<b的最大值
// f2(n,b)=max{f2(n-1,b),f1(n-1,b-costs[n-1])+capacity[n-1]}
// 但是这种方式的时间复杂度是O(n*budget)，所以是不行的
func maxCapacity(costs []int, capacity []int, budget int) int {
	n := len(costs)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = []int{costs[i], capacity[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		}
		return arr[i][1] > arr[j][1]
	})
	// 先做一次过滤，如果cost[i]<cost[j],但是capacity[i]>capacity[j]，那么可以完全不考虑j机器
	var arr2 [][]int
	for i := range arr {
		cost, cp := arr[i][0], arr[i][1]
		if len(arr2) == 0 {
			arr2 = append(arr2, []int{cost, cp})
		} else {
			last := arr[len(arr)-1]
			if cp > last[1] {
				arr2 = append(arr2, []int{cost, cp})
			}
		}
	}
	// 假如只选一个
	res := 0
	// 选两个的场景
	for i := 1; i < budget; i++ {
		idx1 := sort.Search(len(arr2), func(j int) bool {
			return arr2[j][0] > i
		})
		if idx1 > 0 {
			// 第一个选idx-1的下标
			res = max(res, arr2[idx1-1][1])
			if idx1-1 > 0 {
				// 选第二个
				idx2 := sort.Search(idx1, func(j int) bool {
					return arr2[j][0] > budget-arr2[idx1-1][0]-1
				})
				if idx2 > 0 {
					res = max(res, arr2[idx2-1][1]+arr2[idx1-1][1])
				}
			}
		}
	}
	return res
}
