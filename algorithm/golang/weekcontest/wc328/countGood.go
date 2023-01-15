package wc328

import "fmt"

func countGood(nums []int, k int) int64 {
	var res int64
	n := len(nums)
	l := 0
	r := 0
	cnt := 0
	mp := make(map[int]int)
	for l < n {
		for cnt < k && r < n {
			mp[nums[r]]++
			if mp[nums[r]] > 1 {
				cnt += mp[nums[r]] - 1
			}
			r++
		}
		if cnt < k {
			return res
		}
		res += int64(n - r + 1)
		println(fmt.Sprintf("l:%d,r:%d,add:%d", l, r, n-r+1))
		// 左指针移动
		mp[nums[l]]--
		if mp[nums[l]] >= 1 {
			cnt -= mp[nums[l]]
		}
		l++
	}
	return res
}
