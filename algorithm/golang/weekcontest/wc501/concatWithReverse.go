package wc501

func concatWithReverse(nums []int) []int {
	n := len(nums)
	res := make([]int, 2*n)
	for i := 0; i < n; i++ {
		res[i] = nums[i]
	}
	for i := n; i < 2*n; i++ {
		res[i] = nums[n-1-(i-n)]
	}
	return res
}
