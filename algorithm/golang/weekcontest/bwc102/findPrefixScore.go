package bwc102

func findPrefixScore(nums []int) []int64 {
	n := len(nums)
	res := make([]int64, n)
	mx := 0
	var sum int64
	for i, num := range nums {
		if num > mx {
			mx = num
		}
		sum += int64(num) + int64(mx)
		res[i] = sum
	}
	return res
}
