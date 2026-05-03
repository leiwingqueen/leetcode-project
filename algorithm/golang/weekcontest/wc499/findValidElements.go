package wc499

func findValidElements(nums []int) []int {
	n := len(nums)
	left, right := make([]int, n), make([]int, n)
	for i := 1; i < n; i++ {
		left[i] = max(left[i-1], nums[i-1])
	}
	for i := n - 2; i >= 0; i-- {
		right[i] = max(right[i+1], nums[i+1])
	}
	var res []int
	for i := 0; i < n; i++ {
		if nums[i] > left[i] || nums[i] > right[i] {
			res = append(res, nums[i])
		}
	}
	return res
}
