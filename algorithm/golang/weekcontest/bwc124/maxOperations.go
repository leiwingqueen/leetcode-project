package bwc124

func maxOperations(nums []int) int {
	n := len(nums)
	sum := nums[0] + nums[1]
	cnt := 0
	for i := 0; i < n-1; i += 2 {
		if nums[i]+nums[i+1] != sum {
			break
		}
		cnt++
	}
	return cnt
}
