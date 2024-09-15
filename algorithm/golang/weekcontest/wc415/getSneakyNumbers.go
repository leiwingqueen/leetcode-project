package wc415

func getSneakyNumbers(nums []int) []int {
	n := len(nums)
	counter := make([]int, n-2)
	var res []int
	for _, num := range nums {
		counter[num]++
		if counter[num] >= 2 {
			res = append(res, num)
		}
	}
	return res
}
