package wc500

func countOppositeParity(nums []int) []int {
	n := len(nums)
	odd, even := make([]int, n), make([]int, n)
	for i := n - 2; i >= 0; i-- {
		odd[i] = odd[i+1]
		even[i] = even[i+1]
		num := nums[i+1]
		if num%2 == 1 {
			odd[i]++
		} else {
			even[i]++
		}
	}
	res := make([]int, n)
	for i, num := range nums {
		if num%2 == 1 {
			res[i] = even[i]
		} else {
			res[i] = odd[i]
		}
	}
	return res
}
