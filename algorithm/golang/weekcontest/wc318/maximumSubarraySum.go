package wc318

func maximumSubarraySum(nums []int, k int) int64 {
	n := len(nums)
	mp := make(map[int]int)
	var sum int64
	for i := 0; i < k; i++ {
		mp[nums[i]]++
		sum += int64(nums[i])
	}
	var res int64
	if len(mp) == k {
		res = sum
	}
	for i := 1; i <= n-k; i++ {
		sum = sum - int64(nums[i-1]) + int64(nums[i+k-1])
		mp[nums[i-1]]--
		if mp[nums[i-1]] == 0 {
			delete(mp, nums[i-1])
		}
		mp[nums[i+k-1]]++
		if len(mp) == k && sum > res {
			res = sum
		}
	}
	return res
}
