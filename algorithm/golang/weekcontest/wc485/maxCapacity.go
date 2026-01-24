package wc485

// 假设f1(n,b)是选择一台机器<budget的最大值
// f2(n,b)是选择两台机器的<b的最大值
// f2(n,b)=max{f2(n-1,b),f1(n-1,b-costs[n-1])+capacity[n-1]}
// 但是这种方式的时间复杂度是O(n*budget)，所以是不行的
func maxCapacity(costs []int, capacity []int, budget int) int {
	return 0
}
