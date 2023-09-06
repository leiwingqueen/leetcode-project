package bwc112

func maxSum(nums []int, m int, k int) int64 {
	n := len(nums)
	mp := make(map[int]int)
	var sum int64
	for i := 0; i < k; i++ {
		sum += int64(nums[i])
		mp[nums[i]]++
	}
	var res int64
	if len(mp) >= m {
		res = sum
	}
	for i := 1; i <= n-k; i++ {
		sum += int64(nums[i+k-1] - nums[i-1])
		mp[nums[i-1]]--
		if mp[nums[i-1]] == 0 {
			delete(mp, nums[i-1])
		}
		mp[nums[i+k-1]]++
		if len(mp) >= m && sum > res {
			res = sum
		}
	}
	return res
}
