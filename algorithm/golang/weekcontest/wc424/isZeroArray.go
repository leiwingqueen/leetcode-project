package wc424

// 典型的差分数组的题目
func isZeroArray(nums []int, queries [][]int) bool {
	n := len(nums)
	diff := make([]int, n)
	diff[0] = nums[0]
	for i := 1; i < n; i++ {
		diff[i] = nums[i] - nums[i-1]
	}
	for _, query := range queries {
		l, r := query[0], query[1]
		diff[l]--
		if r < n-1 {
			diff[r+1]++
		}
	}
	// 还原数组
	nums[0] = diff[0]
	if nums[0] != 0 {
		return false
	}
	for i := 1; i < n; i++ {
		nums[i] = nums[i-1] + diff[i]
		if nums[i] != 0 {
			return false
		}
	}
	return true
}
