package bwc121

func missingInteger(nums []int) int {
	n := len(nums)
	l, r := 0, 0
	cnt := 0
	res := 0
	sum := 0
	for r < n {
		if l == r || nums[r] == nums[r-1]+1 {
			sum += nums[r]
			r++
		} else {
			if r-l > cnt {
				cnt = r - l
				res = sum
			}
			l = r
			sum = 0
		}
	}
	if r-l > cnt {
		cnt = r - l
		res = sum
	}
	// 找到第一个大于res的数值
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	d := res
	for {
		if !mp[d] {
			return d
		}
		d++
	}
	return sum
}

func missingInteger2(nums []int) int {
	n := len(nums)
	sum := 0
	for i := 0; i < n; i++ {
		if i == 0 || nums[i] == nums[i-1]+1 {
			sum += nums[i]
		} else {
			break
		}
	}
	// 找到第一个大于res的数值
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	d := sum
	for {
		if !mp[d] {
			return d
		}
		d++
	}
	return sum
}
