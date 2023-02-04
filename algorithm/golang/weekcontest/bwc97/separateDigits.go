package bwc97

func separateDigits(nums []int) []int {
	res := make([]int, 0)
	for _, num := range nums {
		tmp := make([]int, 0)
		for num > 0 {
			tmp = append(tmp, num%10)
			num /= 10
		}
		if len(tmp) == 0 {
			res = append(res, 0)
		} else {
			for i := len(tmp) - 1; i >= 0; i-- {
				res = append(res, tmp[i])
			}
		}
	}
	return res
}
