package wc489

func firstUniqueFreq(nums []int) int {
	freq := make(map[int]int)
	for _, num := range nums {
		freq[num]++
	}
	revertFreq := make(map[int]int)
	for _, v := range freq {
		revertFreq[v]++
	}
	for _, num := range nums {
		if revertFreq[freq[num]] == 1 {
			return num
		}
	}
	return -1
}
