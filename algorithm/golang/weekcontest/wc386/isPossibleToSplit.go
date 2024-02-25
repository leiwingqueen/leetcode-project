package wc386

func isPossibleToSplit(nums []int) bool {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
		if mp[num] > 2 {
			return false
		}
	}
	return true
}
