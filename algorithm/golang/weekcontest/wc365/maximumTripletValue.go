package wc365

func maximumTripletValue(nums []int) int64 {
	n := len(nums)
	var res int64
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			for k := j + 1; k < n; k++ {
				s := int64(nums[i]-nums[j]) * int64(nums[k])
				if s > res {
					res = s
				}
			}
		}
	}
	return res
}
