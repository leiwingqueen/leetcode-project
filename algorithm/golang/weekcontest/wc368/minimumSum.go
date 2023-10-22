package wc368

func minimumSum(nums []int) int {
	n := len(nums)
	res := -1
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			for k := j + 1; k < n; k++ {
				if nums[i] < nums[j] && nums[j] > nums[k] && (res < 0 || nums[i]+nums[j]+nums[k] < res) {
					res = nums[i] + nums[j] + nums[k]
				}
			}
		}
	}
	return res
}

func minimumSum2(nums []int) int {
	n := len(nums)
	left, right := make([]int, n), make([]int, n)
	left[1] = nums[0]
	for i := 2; i < n; i++ {
		left[i] = left[i-1]
		if nums[i-1] < left[i] {
			left[i] = nums[i-1]
		}
	}
	right[n-2] = nums[n-1]
	for i := n - 3; i >= 0; i-- {
		right[i] = right[i+1]
		if nums[i+1] < right[i+1] {
			right[i] = nums[i+1]
		}
	}
	res := -1
	for i := 1; i < n-1; i++ {
		if left[i] < nums[i] && right[i] < nums[i] && (res < 0 || left[i]+right[i]+nums[i] < res) {
			res = left[i] + right[i] + nums[i]
		}
	}
	return res
}
