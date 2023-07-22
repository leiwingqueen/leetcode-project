package bwc109

func isGood(nums []int) bool {
	n := len(nums)
	if n < 2 {
		return false
	}
	mp := make(map[int]int)
	for _, num := range nums {
		if num > n-1 {
			return false
		}
		mp[num]++
		if num == n-1 {
			if mp[num] > 2 {
				return false
			}
		} else {
			if mp[num] > 1 {
				return false
			}
		}
	}
	return true
}
