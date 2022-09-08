package bwc86

func findSubarrays(nums []int) bool {
	n := len(nums)
	mp := make(map[int]bool)
	for i := 0; i < n-1; i++ {
		if mp[nums[i]+nums[i+1]] {
			return true
		}
		mp[nums[i]+nums[i+1]] = true
	}
	return false
}
