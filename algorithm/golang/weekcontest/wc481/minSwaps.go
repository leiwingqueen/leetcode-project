package wc481

// 太难了，特别是证明
func minSwaps(nums []int, forbidden []int) int {
	n := len(nums)
	total := make(map[int]int)
	for _, x := range nums {
		total[x]++
	}
	for _, x := range forbidden {
		total[x]++
	}
	for _, v := range total {
		if v > n {
			return -1
		}
	}
	k := 0
	mx := 0
	cnt := make(map[int]int)
	for i := 0; i < n; i++ {
		if nums[i] == forbidden[i] {
			k++
			cnt[nums[i]]++
			mx = max(mx, nums[i])
		}
	}
	return max((k+1)/2, mx)
}
