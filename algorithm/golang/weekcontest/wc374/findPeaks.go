package wc374

func findPeaks(mountain []int) []int {
	n := len(mountain)
	var res []int
	for i := 1; i < n-1; i++ {
		if mountain[i] > mountain[i-1] && mountain[i] > mountain[i+1] {
			res = append(res, i)
		}
	}
	return res
}
