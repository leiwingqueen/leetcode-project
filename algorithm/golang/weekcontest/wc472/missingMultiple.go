package wc472

func missingMultiple(nums []int, k int) int {
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	i := 1
	for mp[i*k] {
		i++
	}
	return i
}
