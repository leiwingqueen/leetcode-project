package bwc103

func maximizeSum(nums []int, k int) int {
	mx := 0
	for _, num := range nums {
		if num > mx {
			mx = num
		}
	}
	return (mx + mx + k - 1) * k / 2
}
