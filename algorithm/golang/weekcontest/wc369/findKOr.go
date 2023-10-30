package wc369

func findKOr(nums []int, k int) int {
	cnt := make([]int, 31)
	for i := 0; i < 31; i++ {
		for _, num := range nums {
			if num&(1<<i) != 0 {
				cnt[i]++
			}
		}
	}
	res := 0
	for i := 0; i < 31; i++ {
		if cnt[i] >= k {
			res |= 1 << i
		}
	}
	return res
}
