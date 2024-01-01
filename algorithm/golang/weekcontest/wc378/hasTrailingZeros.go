package wc378

func hasTrailingZeros(nums []int) bool {
	cnt := 0
	for _, num := range nums {
		if num&0x01 == 0 {
			cnt++
		}
	}
	return cnt >= 2
}
