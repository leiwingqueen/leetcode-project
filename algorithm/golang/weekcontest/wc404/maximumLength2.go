package wc404

// 理解错
func maximumLength2(nums []int, k int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, k)
	}
	dp[0][nums[0]%k] = 1
	for i := 1; i < n; i++ {
		for j := 0; j < k; j++ {
			dp[i][j] = dp[i-1][j]
			if nums[i]%k == j {
				dp[i][j] = max(dp[i][j], dp[i-1][(k-nums[i]%k)%k]+1)
			}
		}
	}
	res := 0
	for i := 0; i < k; i++ {
		res = max(res, dp[n-1][i])
	}
	return res
}

// 修复2
func maximumLength3(nums []int, k int) int {
	n := len(nums)
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, k)
		for j := 0; j < k; j++ {
			dp[i][j] = make([]int, k)
		}
	}
	dp[1][(nums[0]+nums[1])%k][nums[1]%k] = 2
	for i := 2; i < n; i++ {
		for j := 0; j < k; j++ {
			for l := 0; l < k; l++ {
				dp[i][j][l] = dp[i-1][j][l]
				if nums[i]%k == l {
					dp[i][j][l] = max(dp[i][j][l], dp[i-1][j][(j+k-l)%k]+1)
				}
			}
		}
	}
	res := 0
	for i := 0; i < k; i++ {
		for j := 0; j < k; j++ {
			res = max(res, dp[n-1][i][j])
		}
	}
	return res
}

// 内存OOM
func maximumLength4(nums []int, k int) int {
	n := len(nums)
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, k)
		for j := 0; j < k; j++ {
			dp[i][j] = make([]int, k)
		}
	}
	for i := 0; i < k; i++ {
		dp[0][i][nums[0]%k] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < k; j++ {
			// 初始化，已nums[i]为第一个数字
			dp[i][j][nums[i]%k] = 1
			for l := 0; l < k; l++ {
				dp[i][j][l] = max(dp[i][j][l], dp[i-1][j][l])
				if nums[i]%k == l {
					dp[i][j][l] = max(dp[i][j][l], dp[i-1][j][(j+k-l)%k]+1)
				}
			}
		}
	}
	res := 0
	for i := 0; i < k; i++ {
		for j := 0; j < k; j++ {
			res = max(res, dp[n-1][i][j])
		}
	}
	return res
}

// 还是超时，时间复杂度O(n*k*k)
func maximumLength5(nums []int, k int) int {
	n := len(nums)
	pre, next := make([][]int, k), make([][]int, k)
	for i := 0; i < k; i++ {
		pre[i] = make([]int, k)
		next[i] = make([]int, k)
	}
	for i := 0; i < k; i++ {
		pre[i][nums[0]%k] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < k; j++ {
			// 初始化，已nums[i]为第一个数字
			next[j][nums[i]%k] = 1
			for l := 0; l < k; l++ {
				// TODO: 这个循环能否优化一下
				next[j][l] = max(next[j][l], pre[j][l])
				if nums[i]%k == l {
					next[j][l] = max(next[j][l], pre[j][(j+k-l)%k]+1)
				}
			}
		}
		for j := 0; j < k; j++ {
			copy(pre[j], next[j])
		}
	}
	res := 0
	for i := 0; i < k; i++ {
		for j := 0; j < k; j++ {
			res = max(res, next[i][j])
		}
	}
	return res
}

// 不容易想，需要各种优化
func maximumLength6(nums []int, k int) int {
	n := len(nums)
	dp := make([][]int, k)
	for i := 0; i < k; i++ {
		dp[i] = make([]int, k)
	}
	for i := 0; i < k; i++ {
		dp[i][nums[0]%k] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < k; j++ {
			num := nums[i] % k
			// 初始化，已nums[i]为第一个数字
			dp[j][num] = max(1, dp[j][num], dp[j][(j+k-num)%k]+1)
			/*for l := 0; l < k; l++ {
				// 这个循环能否优化一下
				next[j][l] = max(next[j][l], pre[j][l])
				if num == l {
					next[j][l] = max(next[j][l], pre[j][(j+k-l)%k]+1)
				}
			}*/
		}
		/*for j := 0; j < k; j++ {
			copy(pre[j], next[j])
		}*/
	}
	res := 0
	for i := 0; i < k; i++ {
		for j := 0; j < k; j++ {
			res = max(res, dp[i][j])
		}
	}
	return res
}
