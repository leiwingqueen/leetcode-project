package array

func numberOfPairs(nums []int) []int {
	MX := 100
	cnt := make([]int, MX+1)
	for _, num := range nums {
		cnt[num]++
	}
	res := []int{0, 0}
	for i := 0; i <= MX; i++ {
		if cnt[i] == 0 {
			continue
		}
		res[0] += cnt[i] / 2
		res[1] += cnt[i] % 2
	}
	return res
}
