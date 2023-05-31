package greedy

import "math"

// 这么穷举果然会超时
func mctFromLeafValues(arr []int) int {
	// 非叶子节点的最小的和，叶子节点的最大值
	var dfs func(l, r int) (int, int)
	dfs = func(l, r int) (int, int) {
		if l == r {
			return 0, arr[l]
		}
		// 把[l,r]分到两颗子树
		res := math.MaxInt
		childMx := 0
		for i := l; i < r; i++ {
			//[l,i],[i+1,r]
			s1, s2 := dfs(l, i)
			s3, s4 := dfs(i+1, r)
			sub := s2*s4 + s1 + s3
			if sub < res {
				res = sub
				if s2 > s4 {
					childMx = s2
				} else {
					childMx = s4
				}
			}
		}
		return res, childMx
	}
	res, _ := dfs(0, len(arr)-1)
	return res
}

// 增加记忆，能通过，但这里其实可以改成dp?
func mctFromLeafValues2(arr []int) int {
	n := len(arr)
	mem := make([][][]int, n)
	for i := 0; i < n; i++ {
		mem[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			mem[i][j] = []int{-1, 0}
		}
	}
	// 非叶子节点的最小的和，叶子节点的最大值
	var dfs func(l, r int) (int, int)
	dfs = func(l, r int) (int, int) {
		if l == r {
			return 0, arr[l]
		}
		if mem[l][r][0] > 0 {
			return mem[l][r][0], mem[l][r][1]
		}
		// 把[l,r]分到两颗子树
		res := math.MaxInt
		childMx := 0
		for i := l; i < r; i++ {
			//[l,i],[i+1,r]
			s1, s2 := dfs(l, i)
			s3, s4 := dfs(i+1, r)
			sub := s2*s4 + s1 + s3
			if sub < res {
				res = sub
				if s2 > s4 {
					childMx = s2
				} else {
					childMx = s4
				}
			}
		}
		mem[l][r][0], mem[l][r][1] = res, childMx
		return res, childMx
	}
	res, _ := dfs(0, len(arr)-1)
	return res
}

// dp解法，终于击败100%的用户
func mctFromLeafValues3(arr []int) int {
	n := len(arr)
	// 计算每个区间范围的最大值
	mxMatrix := make([][]int, n)
	for i := 0; i < n; i++ {
		mxMatrix[i] = make([]int, n)
		mx := 0
		for j := i; j < n; j++ {
			if arr[j] > mx {
				mx = arr[j]
			}
			mxMatrix[i][j] = mx
		}
	}
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
		for j := i + 1; j < n; j++ {
			dp[i][j] = -1
		}
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			for l := i; l < j; l++ {
				sub := dp[i][l] + dp[l+1][j] + mxMatrix[i][l]*mxMatrix[l+1][j]
				if dp[i][j] < 0 || sub < dp[i][j] {
					dp[i][j] = sub
				}
			}
		}
	}
	return dp[0][n-1]
}
