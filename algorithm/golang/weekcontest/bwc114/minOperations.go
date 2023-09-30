package bwc114

func minOperations(nums []int, k int) int {
	n := len(nums)
	counter := make([]int, n+1)
	p := n - 1
	cnt := 0
	for cnt < k {
		counter[nums[p]]++
		if counter[nums[p]] == 1 && nums[p] <= k {
			cnt++
		}
		p--
	}
	return n - p - 1
}
