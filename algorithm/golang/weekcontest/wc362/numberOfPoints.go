package wc362

func numberOfPoints(nums [][]int) int {
	cnt := make([]bool, 101)
	for _, r := range nums {
		start, end := r[0], r[1]
		for i := start; i <= end; i++ {
			cnt[i] = true
		}
	}
	res := 0
	for _, c := range cnt {
		if c {
			res++
		}
	}
	return res
}
