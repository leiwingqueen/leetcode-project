package wc315

func findMaxK(nums []int) int {
	res := -1
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
		if mp[-num] && (res < 0 || abs(num) > res) {
			res = abs(num)
		}
	}
	return res
}

func abs(num int) int {
	if num < 0 {
		return -num
	} else {
		return num
	}
}
