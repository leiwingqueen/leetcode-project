package wc312

func longestSubarray(nums []int) int {
	n := len(nums)
	mx := 0
	for i := 0; i < n; i++ {
		if nums[i] > mx {
			mx = nums[i]
		}
	}
	res := 0
	l := 0
	r := 0
	for r < n {
		for l < n && nums[l]&mx != mx {
			l++
		}
		if l == n {
			return res
		}
		r = l
		for r < n && nums[r]&mx == mx {
			r++
		}
		if r-l > res {
			res = r - l
		}
		l = r
	}
	return res
}
