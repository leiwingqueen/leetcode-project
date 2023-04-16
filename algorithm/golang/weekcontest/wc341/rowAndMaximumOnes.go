package wc341

func rowAndMaximumOnes(mat [][]int) []int {
	res := []int{-1, -1}
	for i, col := range mat {
		sum := 0
		for _, num := range col {
			if num == 1 {
				sum++
			}
		}
		if sum > res[1] {
			res[0], res[1] = i, sum
		}
	}
	return res
}
