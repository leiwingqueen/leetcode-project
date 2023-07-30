package wc356

func countCompleteSubarrays(nums []int) int {
	n := len(nums)
	mp := make(map[int]struct{})
	for _, num := range nums {
		mp[num] = struct{}{}
	}
	expect := len(mp)
	l, r := 0, 0
	res := 0
	mp2 := make([]int, 2001)
	cnt := 0
	for r < n {
		mp2[nums[r]]++
		if mp2[nums[r]] == 1 {
			cnt++
		}
		for cnt == expect {
			res += n - r
			mp2[nums[l]]--
			if mp2[nums[l]] == 0 {
				cnt--
			}
			l++
		}
		r++
	}
	return res
}
