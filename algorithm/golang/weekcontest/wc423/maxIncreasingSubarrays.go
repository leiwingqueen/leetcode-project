package wc423

func maxIncreasingSubarrays(nums []int) int {
	n := len(nums)
	var arr []int
	l, r := 0, 0
	for r < n {
		if l == r || nums[r] > nums[r-1] {
			r++
		} else {
			arr = append(arr, r-l)
			l = r
		}
	}
	arr = append(arr, r-l)
	res := arr[0] / 2
	for i := 1; i < len(arr); i++ {
		res = max(res, arr[i]/2, min(arr[i], arr[i-1]))
	}
	return res
}
