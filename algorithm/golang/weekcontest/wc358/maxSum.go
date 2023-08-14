package wc358

func maxSum(nums []int) int {
	mxDigit := func(num int) int {
		res := 0
		for num > 0 {
			k := num % 10
			if k > res {
				res = k
			}
			num /= 10
		}
		return res
	}
	n := len(nums)
	res := -1
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if mxDigit(nums[i]) == mxDigit(nums[j]) && nums[i]+nums[j] > res {
				res = nums[i] + nums[j]
			}
		}
	}
	return res
}
