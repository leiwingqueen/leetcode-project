package wc436

// 先暴力先一个，超时
func assignElements(groups []int, elements []int) []int {
	n := len(groups)
	res := make([]int, n)
	for i, group := range groups {
		res[i] = -1
		for j, element := range elements {
			if group%element == 0 {
				res[i] = j
				break
			}
		}
	}
	return res
}
