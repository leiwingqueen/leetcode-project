package wc427

func constructTransformedArray(nums []int) []int {
	n := len(nums)
	res := make([]int, n)
	for i, num := range nums {
		if num == 0 {
			res[i] = num
		} else if num > 0 {
			num %= n
			res[i] = nums[(i+num)%n]
		} else {
			num = -num
			num %= n
			res[i] = nums[(i+n-num)%n]
		}
	}
	return res
}
