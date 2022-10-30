package wc317

func averageValue(nums []int) int {
	sum := 0
	cnt := 0
	for _, num := range nums {
		if num%2 == 0 && num%3 == 0 {
			sum += num
			cnt++
		}
	}
	if cnt == 0 {
		return 0
	} else {
		return sum / cnt
	}
}
