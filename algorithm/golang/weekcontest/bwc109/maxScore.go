package bwc109

// 必然超时
func maxScore(nums []int, x int) int64 {
	n := len(nums)
	dp := make([]int64, n)
	dp[0] = int64(nums[0])
	res := int64(nums[0])
	for i := 1; i < n; i++ {
		for j := i - 1; j >= 0; j-- {
			s := dp[j] + int64(nums[i])
			if nums[i]%2 != nums[j]%2 {
				s -= int64(x)
			}
			if s > dp[i] {
				dp[i] = s
			}
		}
		if dp[i] > res {
			res = dp[i]
		}
	}
	return res
}

// 优化解法
func maxScore2(nums []int, x int) int64 {
	n := len(nums)
	arr1 := make([]int, n)
	arr2 := make([]int, n)
	if nums[0]%2 == 0 {
		arr1[0] = 0
		arr2[0] = -1
	} else {
		arr1[0] = -1
		arr2[0] = 0
	}
	for i := 1; i < n; i++ {
		if nums[i]%2 == 0 {
			arr1[i] = i
			arr2[i] = arr2[i-1]
		} else {
			arr2[i] = i
			arr1[i] = arr1[i-1]
		}
	}
	dp := make([]int64, n)
	dp[0] = int64(nums[0])
	res := int64(nums[0])
	for i := 1; i < n; i++ {
		if nums[i]%2 == 0 {
			if arr1[i-1] >= 0 {
				s := dp[arr1[i-1]] + int64(nums[i])
			} else {
				
			}
		}
		for j := i - 1; j >= 0; j-- {
			s := dp[j] + int64(nums[i])
			if nums[i]%2 != nums[j]%2 {
				s -= int64(x)
			}
			if s > dp[i] {
				dp[i] = s
			}
		}
		if dp[i] > res {
			res = dp[i]
		}
	}
	return res
}
