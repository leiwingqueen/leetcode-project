package wc529

func countDominantElements(nums []int) int {
	res := 0
	for i, num := range nums {
		cnt := 0
		sum := 0
		if i-1 >= 0 {
			cnt++
			sum += nums[i-1]
		}
		if i+1 < len(nums) {
			cnt++
			sum += nums[i+1]
		}
		if cnt == 0 {
			res++
		} else {
			if float64(num) >= float64(sum)/float64(cnt) {
				res++
			}
		}
	}
	return res
}
