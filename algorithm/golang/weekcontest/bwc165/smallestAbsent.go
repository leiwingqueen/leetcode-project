package bwc165

func smallestAbsent(nums []int) int {
	n := len(nums)
	mp := make(map[int]bool)
	sum := 0
	for i := range nums {
		sum += nums[i]
		mp[nums[i]] = true
	}
	avg := sum / n
	item := max(1, avg+1)
	for {
		if !mp[item] {
			return item
		}
		item++
	}
}
