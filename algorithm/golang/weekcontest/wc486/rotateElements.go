package wc486

func rotateElements(nums []int, k int) []int {
	var positiveNums []int
	for _, num := range nums {
		if num >= 0 {
			positiveNums = append(positiveNums, num)
		}
	}
	n := len(positiveNums)
	if n == 0 {
		return nums
	}
	k %= n
	// 左移
	positiveNew := make([]int, n)
	for i := 0; i < n; i++ {
		positiveNew[(i+n-k)%n] = positiveNums[i]
	}
	// 更新
	j := 0
	for i := 0; i < len(nums); i++ {
		if nums[i] >= 0 {
			nums[i] = positiveNew[j]
			j++
		}
	}
	return nums
}
