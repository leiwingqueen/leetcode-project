package wc348

func semiOrderedPermutation(nums []int) int {
	n := len(nums)
	p1, p2 := 0, 0
	for i, num := range nums {
		if num == 1 {
			p1 = i
		} else if num == n {
			p2 = i
		}
	}
	res := p1 + n - p2 - 1
	if p1 > p2 {
		res -= 1
	}
	return res
}
