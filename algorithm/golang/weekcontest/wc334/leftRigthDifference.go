package wc334

func leftRigthDifference(nums []int) []int {
	n := len(nums)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		s1 := 0
		for j := 0; j < i; j++ {
			s1 += nums[j]
		}
		s2 := 0
		for j := i + 1; j < n; j++ {
			s2 += nums[j]
		}
		res[i] = s1 - s2
		if res[i] < 0 {
			res[i] = -res[i]
		}
	}
	return res
}
