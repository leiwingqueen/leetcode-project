package bwc116

// 暴力解法
func sumCounts(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			mp := make(map[int]struct{})
			for l := i; l <= j; l++ {
				mp[nums[l]] = struct{}{}
			}
			res = (res + len(mp)*len(mp)) % mod
		}
	}
	return res
}

func sumCounts2(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			mp := make(map[int]struct{})
			for l := i; l <= j; l++ {
				mp[nums[l]] = struct{}{}
			}
			res = (res + len(mp)*len(mp)) % mod
		}
	}
	return res
}
