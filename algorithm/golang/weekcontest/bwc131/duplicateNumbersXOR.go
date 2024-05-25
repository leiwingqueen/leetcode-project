package bwc131

func duplicateNumbersXOR(nums []int) int {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	res := 0
	for k, v := range mp {
		if v == 2 {
			res ^= k
		}
	}
	return res
}
