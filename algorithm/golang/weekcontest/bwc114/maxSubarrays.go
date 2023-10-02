package bwc114

// [5,7,1,3]
// 101,111,001,011
// 假设某一位是0，那么所有子数组至少要包含一个0
// 假设某一位是1，那么最多只有一个字数组含有一个1
func maxSubarrays(nums []int) int {
	and := nums[0]
	for _, num := range nums {
		and &= num
	}
	if and > 0 {
		return 0
	}
	and = -1
	cnt := 0
	for _, num := range nums {
		and &= num
		if and == 0 {
			cnt++
			and = -1
		}
	}
	return cnt
}
