package bwc139

func stableMountains(height []int, threshold int) []int {
	n := len(height)
	var res []int
	for i := 1; i < n; i++ {
		if height[i-1] > threshold {
			res = append(res, i)
		}
	}
	return res
}
