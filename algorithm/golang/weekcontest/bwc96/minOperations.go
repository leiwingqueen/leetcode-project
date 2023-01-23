package bwc96

func minOperations(nums1 []int, nums2 []int, k int) int64 {
	n := len(nums1)
	diff := make([]int64, n)
	var positive int64
	var sum int64
	for i, c1 := range nums1 {
		diff[i] = int64(c1) - int64(nums2[i])
		if k == 0 {
			if diff[i] != 0 {
				return -1
			}
		} else {
			if diff[i]%int64(k) != 0 {
				return -1
			}
		}
		sum += diff[i]
		if diff[i] > 0 {
			positive += diff[i]
		}
	}
	if k == 0 {
		return 0
	}
	if sum != 0 {
		return -1
	}
	return positive / int64(k)
}
