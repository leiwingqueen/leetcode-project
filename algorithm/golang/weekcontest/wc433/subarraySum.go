package wc433

func subarraySum(nums []int) int {
	n := len(nums)
	prefix := make([]int, n+1)
	for i, num := range nums {
		prefix[i+1] = prefix[i] + num
	}
	res := 0
	for i, num := range nums {
		start := max(0, i-num)
		res += prefix[i+1] - prefix[start]
	}
	return res
}
