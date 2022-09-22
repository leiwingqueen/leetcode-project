package bwc84

func countBadPairs(nums []int) int64 {
	mp := make(map[int]int)
	for i, num := range nums {
		mp[num-i]++
	}
	var sum int64
	for _, v := range mp {
		sum += int64(v) * int64(v-1) / 2
	}
	n := len(nums)
	return int64(n)*int64(n-1)/2 - sum
}
