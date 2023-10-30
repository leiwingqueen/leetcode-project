package wc369

func minSum(nums1 []int, nums2 []int) int64 {
	max := func(a, b int64) int64 {
		if a > b {
			return a
		} else {
			return b
		}
	}
	s1, s2 := int64(0), int64(0)
	cnt1, cnt2 := 0, 0
	for _, num := range nums1 {
		s1 += int64(num)
		if num == 0 {
			cnt1++
		}
	}
	for _, num := range nums2 {
		s2 += int64(num)
		if num == 0 {
			cnt2++
		}
	}
	diff := s2 - s1
	if cnt1 == 0 && cnt2 == 0 {
		if diff == 0 {
			return s1
		} else {
			return -1
		}
	} else if cnt1 == 0 {
		if -diff >= int64(cnt2) {
			return s1
		} else {
			return -1
		}
	} else if cnt2 == 0 {
		if diff >= int64(cnt1) {
			return s2
		} else {
			return -1
		}
	} else {
		return s1 + max(int64(cnt1), int64(cnt2)+diff)
	}
}
