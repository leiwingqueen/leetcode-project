package wc354

func minimumIndex(nums []int) int {
	n := len(nums)
	if n == 1 {
		return -1
	}
	mp := make(map[int]int)
	// 过半数的数字
	mx := 0
	for _, num := range nums {
		mp[num]++
		if mp[num] > n/2 {
			mx = num
		}
	}
	c := 0
	for i := 0; i < n-1; i++ {
		num := nums[i]
		if num == mx {
			c++
		}
		if c > (i+1)/2 && mp[mx]-c > (n-i-1)/2 {
			return i
		}
	}
	return -1
}
