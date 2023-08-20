package bwc111

func countPairs(nums []int, target int) int {
	n := len(nums)
	cnt := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if nums[i]+nums[j] < target {
				cnt++
			}
		}
	}
	return cnt
}
