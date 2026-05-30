package wc503

func limitOccurrences(nums []int, k int) []int {
	n := len(nums)
	var res []int
	l, r := 0, 0
	for r < n {
		if nums[l] != nums[r] {
			l = r
		} else {
			if r-l < k {
				res = append(res, nums[r])
			}
			r++
		}
	}
	return res
}
