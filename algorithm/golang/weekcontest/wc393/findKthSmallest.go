package wc393

func findKthSmallest(coins []int, k int) int64 {
	// 小于或等于num的数量有多少
	getCount := func(num int64) int {
		cnt := 0
		for _, coin := range coins {
			cnt += num / coin
		}
		return cnt
	}
}
