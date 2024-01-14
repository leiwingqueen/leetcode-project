package wc380

func maxFrequencyElements(nums []int) int {
	mp := make(map[int]int)
	mx := 0
	for _, num := range nums {
		mp[num]++
		if mp[num] > mx {
			mx = mp[num]
		}
	}
	res := 0
	for _, v := range mp {
		if v == mx {
			res += v
		}
	}
	return res
}
