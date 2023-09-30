package bwc114

func minOperations2(nums []int) int {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	res := 0
	for _, v := range mp {
		if v == 1 {
			return -1
		}
		if v%3 == 0 {
			res += v / 3
		} else if v%3 == 1 {
			res += (v-3)/3 + 2
		} else {
			res += v/3 + 1
		}
	}
	return res
}
