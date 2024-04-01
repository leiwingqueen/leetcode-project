package wc391

func countAlternatingSubarrays(nums []int) int64 {
	n := len(nums)
	l, r := 0, 0
	var res int64
	for r < n {
		if r == l || nums[r] != nums[r-1] {
			r++
		} else {
			//[l,r)之间的所有组合
			res += int64(1+r-l) * int64(r-l) / 2
			l = r
		}
	}
	res += int64(1+r-l) * int64(r-l) / 2
	return res
}
