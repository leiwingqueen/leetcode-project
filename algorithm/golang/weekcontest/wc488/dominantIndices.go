package wc488

// 从右向左遍历即可
func dominantIndices(nums []int) int {
	n := len(nums)
	res := 0
	sum := nums[n-1]
	cnt := 1
	for i := n - 2; i >= 0; i-- {
		if nums[i] > sum/cnt {
			res++
		}
		sum += nums[i]
		cnt++
	}
	return res
}
