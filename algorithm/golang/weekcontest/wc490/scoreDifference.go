package wc490

func scoreDifference(nums []int) int {
	diff := 0
	positive := true
	for i, p := range nums {
		if p%2 == 1 {
			positive = !positive
		}
		if (i+1)%6 == 0 {
			positive = !positive
		}
		if positive {
			diff += p
		} else {
			diff -= p
		}
	}
	return diff
}
