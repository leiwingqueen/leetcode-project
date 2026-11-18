package wc528

func countConsistent(nums []int) int {
	n := len(nums)
	check := func(i, j int) bool {
		minNum := nums[i]
		and := nums[i]
		for p := i; p <= j; p++ {
			minNum = min(minNum, nums[p])
			and &= nums[p]
		}
		return and == minNum
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			if check(i, j) {
				res++
			}
		}
	}
	return res
}

func countConsistent2(nums []int) int {
	n := len(nums)
	res := 0
	for i := range nums {
		l := i - 1
		// nums[l] != nums[i] 这里是为了避免重复计算，如果有相同的元素，那么认为最小的是靠左的那个
		for l >= 0 && nums[l] != nums[i] && nums[l]&nums[i] == nums[i] {
			l--
		}
		c1 := i - l
		r := i + 1
		for r < n && nums[r]&nums[i] == nums[i] {
			r++
		}
		c2 := r - i
		res += c1 * c2
	}
	return res
}

// 还差4个用例就能pass
func countConsistent3(nums []int) int64 {
	n := len(nums)
	var res int64
	for i := range nums {
		l := i - 1
		// nums[l] != nums[i] 这里是为了避免重复计算，如果有相同的元素，那么认为最小的是靠左的那个
		for l >= 0 && nums[l] != nums[i] && nums[l]&nums[i] == nums[i] {
			l--
		}
		c1 := i - l
		r := i + 1
		// 这里计算出来的nums[r]一定是>=nums[i]的
		for r < n && nums[r]&nums[i] == nums[i] {
			r++
		}
		c2 := r - i
		res += int64(c1) * int64(c2)
	}
	return res
}
