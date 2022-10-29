package bwc90

func secondGreaterElement(nums []int) []int {
	n := len(nums)
	s := make([]int, 0)
	right := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for len(s) > 0 && nums[s[len(s)-1]] <= nums[i] {
			s = s[0 : len(s)-1]
		}
		right[i] = -1
		if len(s) > 0 {
			right[i] = s[len(s)-1]
		}
		s = append(s, i)
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		res[i] = -1
		if right[i] >= 0 {
			j := right[i] + 1
			for ; j < n; j++ {
				if nums[j] > nums[i] {
					break
				}
			}
			if j < n {
				res[i] = nums[j]
			}
		}
	}
	return res
}
