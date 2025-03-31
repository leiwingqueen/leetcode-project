package wc443

func minCosts(cost []int) []int {
	n := len(cost)
	res := make([]int, n)
	minCost := cost[0]
	for i := 0; i < n; i++ {
		res[i] = min(minCost, cost[i])
		minCost = res[i]
	}
	return res
}
