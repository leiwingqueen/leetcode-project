package wc424

func countValidSelections(nums []int) int {
	n := len(nums)
	var check func(arr []int, idx, dir, total int) bool
	check = func(arr []int, idx int, dir int, total int) bool {
		if total == 0 {
			return true
		}
		if idx >= n || idx < 0 {
			return false
		}
		if arr[idx] == 0 {
			return check(arr, idx+dir, dir, total)
		} else {
			arr[idx]--
			r := check(arr, idx-dir, -dir, total-1)
			arr[idx]++
			return r
		}
	}
	total := 0
	for _, num := range nums {
		total += num
	}
	res := 0
	for i, num := range nums {
		if num == 0 {
			if check(nums, i, 1, total) {
				res++
			}
			if check(nums, i, -1, total) {
				res++
			}
		}
	}
	return res
}
