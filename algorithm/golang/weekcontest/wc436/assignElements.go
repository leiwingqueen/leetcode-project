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

// 还是超时
func assignElements2(groups []int, elements []int) []int {
	n := len(groups)
	mx := 0
	for _, group := range groups {
		mx = max(mx, group)
	}
	tmp := make([]int, mx+1)
	for i, element := range elements {
		for j := element; j <= mx; j += element {
			if tmp[j] == 0 {
				tmp[j] = i + 1
			}
		}
	}
	res := make([]int, n)
	for i, group := range groups {
		res[i] = tmp[group] - 1
	}
	return res
}

func assignElements3(groups []int, elements []int) []int {
	n := len(groups)
	mx := 0
	for _, group := range groups {
		mx = max(mx, group)
	}
	tmp := make([]int, mx+1)
	for i, element := range elements {
		if element > mx || tmp[element] > 0 {
			continue
		}
		for j := element; j <= mx; j += element {
			if tmp[j] == 0 {
				tmp[j] = i + 1
			}
		}
	}
	res := make([]int, n)
	for i, group := range groups {
		res[i] = tmp[group] - 1
	}
	return res
}
