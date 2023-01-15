package wc328

func differenceOfSum(nums []int) int {
	s1 := 0
	s2 := 0
	for _, num := range nums {
		s1 += num
		for num > 0 {
			s2 += num % 10
			num /= 10
		}
	}
	if s1 > s2 {
		return s1 - s2
	} else {
		return s2 - s1
	}
}
