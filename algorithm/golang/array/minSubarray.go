package array

// 超出时间限制
func minSubarray(nums []int, p int) int {
	n := len(nums)
	sum := 0
	for _, num := range nums {
		sum += num
	}
	mod := sum % p
	if mod == 0 {
		return 0
	}
	prefixSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		prefixSum[i+1] = prefixSum[i] + nums[i]
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= n-i; j++ {
			// [j,j+i)
			s := prefixSum[j+i] - prefixSum[j]
			if s%p == mod {
				return i
			}
		}
	}
	return -1
}
