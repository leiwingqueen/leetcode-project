package wc349

func minCost(nums []int, x int) int64 {
	n := len(nums)
	if n == 1 {
		return int64(nums[0])
	}
	var res int64
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		res += int64(nums[i])
		arr[i] = nums[i]
	}
	for k := 1; k < n; k++ {
		// 向左移动的偏移量
		var diff int64
		for i := 0; i < n; i++ {
			if nums[(i+k)%n] < arr[i] {
				diff += int64(arr[i] - nums[(i+k)%n])
				arr[i] = nums[(i+k)%n]
			}
		}
		if diff > 0 && diff > int64(x) {
			res -= diff - int64(x)
		}
	}
	return res
}
