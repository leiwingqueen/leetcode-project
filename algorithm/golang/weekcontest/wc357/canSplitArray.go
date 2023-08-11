package wc357

func canSplitArray(nums []int, m int) bool {
	n := len(nums)
	preSum := make([]int, n+1)
	for i, num := range nums {
		preSum[i+1] = preSum[i] + num
	}
	mem := make([][]int, n)
	for i := 0; i < n; i++ {
		mem[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		mem[i][i] = 1
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i == j {
			return true
		}
		if mem[i][j] != 0 {
			return mem[i][j] == 1
		}
		for k := i; k < j; k++ {
			s1 := preSum[k+1] - preSum[i]
			s2 := preSum[j+1] - preSum[k+1]
			if (k == i || s1 >= m) && (k+1 == j || s2 >= m) &&
				dfs(i, k) && dfs(k+1, j) {
				mem[i][j] = 1
				return true
			}
		}
		mem[i][j] = 2
		return false
	}
	return dfs(0, n-1)
}
