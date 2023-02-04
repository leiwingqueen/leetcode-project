package bwc97

func maxCount(banned []int, n int, maxSum int) int {
	mp := make(map[int]bool)
	for _, num := range banned {
		mp[num] = true
	}
	cnt := 0
	sum := 0
	for i := 1; i <= n; i++ {
		if !mp[i] {
			sum += i
			if sum > maxSum {
				return cnt
			}
			cnt++
		}
	}
	return cnt
}
