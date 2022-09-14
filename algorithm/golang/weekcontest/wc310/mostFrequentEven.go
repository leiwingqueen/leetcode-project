package wc310

func mostFrequentEven(nums []int) int {
	mp := make(map[int]int)
	res := -1
	for _, num := range nums {
		if num%2 == 0 {
			mp[num]++
			if res < 0 || mp[num] > mp[res] || (mp[num] == mp[res] && num < res) {
				res = num
			}
		}
	}
	return res
}
