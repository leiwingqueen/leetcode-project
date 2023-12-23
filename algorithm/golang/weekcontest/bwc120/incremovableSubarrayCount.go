package bwc120

// 暴力
func incremovableSubarrayCount(nums []int) int {
	n := len(nums)
	check := func(i, j int) bool {
		for p := 1; p < i; p++ {
			if nums[p] <= nums[p-1] {
				return false
			}
		}
		for p := j + 2; p < n; p++ {
			if nums[p] <= nums[p-1] {
				return false
			}
		}
		if j+1 < n && i-1 >= 0 {
			return nums[j+1] > nums[i-1]
		} else {
			return true
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			// 删除[i,j]，剩余[0,i),[j+1,n)保证递增
			if check(i, j) {
				res++
			}
		}
	}
	return res
}

func incremovableSubarrayCount2(nums []int) int64 {
	n := len(nums)
	search := func(start int, x int) int {
		l, r := start, n-1
		if nums[r] <= x {
			return n
		}
		for l < r {
			mid := l + (r-l)/2
			if nums[mid] > x {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	// 从后往前，找到最大的连续递增的下标
	p2 := n - 2
	for p2 >= 0 && nums[p2] < nums[p2+1] {
		p2--
	}
	p2++
	var res int64
	for i := 0; i < n; i++ {
		if i > 1 && nums[i-1] <= nums[i-2] {
			break
		}
		// 删除节点的左边界为i
		x := -1
		if i > 0 {
			x = nums[i-1]
		}
		// 从[p2,n)找到最小的大于nums[i]的下标
		idx := search(p2, x)
		if idx <= i {
			idx = i + 1
		}
		res += int64(n - idx + 1)
	}
	return res
}
