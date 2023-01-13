package wc327

func maximumCount(nums []int) int {
	// >0 的第一个下标
	n := len(nums)
	search1 := func() int {
		if nums[n-1] <= 0 {
			return -1
		}
		l := 0
		r := n - 1
		for l < r {
			mid := l + (r-l)/2
			if nums[mid] > 0 {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	// <0的最后一个下标
	search2 := func() int {
		if nums[0] >= 0 {
			return -1
		}
		l := 0
		r := n - 1
		for l < r {
			mid := l + (r-l+1)/2
			if nums[mid] < 0 {
				l = mid
			} else {
				r = mid - 1
			}
		}
		return l
	}
	idx1 := search1()
	idx2 := search2()
	cnt1 := 0
	if idx1 >= 0 {
		cnt1 = n - idx1
	}
	cnt2 := 0
	if idx2 >= 0 {
		cnt2 = idx2 + 1
	}
	if cnt1 > cnt2 {
		return cnt1
	} else {
		return cnt2
	}
}
