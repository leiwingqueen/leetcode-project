package wc429

// 思路反过来，从右往左添加元素，看至少需要增加到哪里
func minimumOperations(nums []int) int {
	mp := make(map[int]int)
	n := len(nums)
	p := 0
	for i := n - 1; i >= 0; i-- {
		mp[nums[i]]++
		if mp[nums[i]] > 1 {
			p = i + 1
			break
		}
	}
	// [p,n)为最小满足条件的
	return (p + 2) / 3
}
