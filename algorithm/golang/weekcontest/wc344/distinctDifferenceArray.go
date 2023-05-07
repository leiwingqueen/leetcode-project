package wc344

func distinctDifferenceArray(nums []int) []int {
	n := len(nums)
	mp1, mp2 := make(map[int]struct{}), make(map[int]struct{})
	d1, d2 := make([]int, n), make([]int, n)
	for i := 0; i < n; i++ {
		mp1[nums[i]] = struct{}{}
		d1[i] = len(mp1)
	}
	for i := n - 1; i >= 0; i-- {
		mp2[nums[i]] = struct{}{}
		d2[i] = len(mp2)
	}
	res := make([]int, n)
	for i := 0; i < n-1; i++ {
		res[i] = d1[i] - d2[i+1]
	}
	res[n-1] = d1[n-1]
	return res
}
