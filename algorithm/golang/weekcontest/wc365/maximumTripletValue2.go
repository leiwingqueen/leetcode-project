package wc365

// 1,3,4,10,19
func maximumTripletValue2(nums []int) int64 {
	max := func(a, b int64) int64 {
		if a > b {
			return a
		} else {
			return b
		}
	}
	// 求前n个数的最大差值为f(n)=max{f(n-1),mx-nums[n-1]}
	n := len(nums)
	i, j, k := 0, 1, 2
	mx := nums[i]
	var res int64
	fn := int64(nums[0] - nums[1])
	for k < n {
		fn = max(fn, int64(mx)-int64(nums[j]))
		res = max(res, fn*int64(nums[k]))
		if nums[j] > mx {
			mx = nums[j]
		}
		j++
		k++
	}
	return res
}
