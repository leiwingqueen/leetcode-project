package bwc119

func maxSubarrayLength(nums []int, k int) int {
	n := len(nums)
	mp := make(map[int]int)
	l, r := 0, 0
	res := 0
	for r < n {
		for r < n && mp[nums[r]] < k {
			mp[nums[r]]++
			r++
		}
		size := r - l
		if size > res {
			res = size
		}
		if r == n {
			break
		}
		for nums[l] != nums[r] {
			mp[nums[l]]--
			l++
		}
		mp[nums[l]]--
		l++
	}
	return res
}
