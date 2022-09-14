package array

import "sort"

func trimMean(arr []int) float64 {
	sort.Ints(arr)
	n := len(arr)
	trim := (int)(float64(n) * 0.05)
	sum := 0
	for i := trim; i < n-trim; i++ {
		sum += arr[i]
	}
	return float64(sum) / float64(n-2*trim)
}
