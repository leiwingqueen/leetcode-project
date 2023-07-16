package wc354

func sumOfSquares(nums []int) int {
	n := len(nums)
	res := 0
	for i, num := range nums {
		if n%(i+1) == 0 {
			res += num * num
		}
	}
	return res
}
