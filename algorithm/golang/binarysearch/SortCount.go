package binarysearch

func search(nums []int, target int) int {
	if len(nums) == 0 {
		return 0
	}
	idx := binarySearch(nums, target)
	if idx < 0 {
		return 0
	}
	cnt := 0
	for i := idx; i < len(nums) && nums[i] == target; idx++ {
		cnt++
	}
	return cnt
}

func binarySearch(nums []int, target int) int {
	l := 0
	r := len(nums) - 1
	for l < r {
		mid := l + (r-l)/2
		if nums[mid] >= target {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if nums[l] == target {
		return l
	} else {
		return -1
	}
}
