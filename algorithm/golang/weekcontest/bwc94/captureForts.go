package bwc94

func captureForts(forts []int) int {
	n := len(forts)
	j := 0
	res := 0
	for i, num := range forts {
		if num == 1 {
			j = i + 1
			for j < n && forts[j] == 0 {
				j++
			}
			if j < n && forts[j] == -1 && j-i-1 > res {
				res = j - i - 1
			}
		} else if num == -1 {
			j = i + 1
			for j < n && forts[j] == 0 {
				j++
			}
			if j < n && forts[j] == 1 && j-i-1 > res {
				res = j - i - 1
			}
		}
	}
	return res
}
