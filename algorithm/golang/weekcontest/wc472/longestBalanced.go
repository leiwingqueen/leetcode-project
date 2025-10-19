package wc472

// 没有什么特别好的思路，尝试用暴力解法来解决
func longestBalanced(nums []int) int {
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		mp := make(map[int]int)
		even := 0
		odd := 0
		for j := i; j < n; j++ {
			num := nums[j]
			if num%2 == 0 {
				if mp[num] == 0 {
					even++
				}
			} else {
				if mp[num] == 0 {
					odd++
				}
			}
			mp[num]++
			if even == odd {
				res = max(res, j-i+1)
			}
		}
	}
	return res
}
