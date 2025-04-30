package math

func findNumbers(nums []int) int {
	getLen := func(num int) int {
		if num == 0 {
			return 1
		}
		size := 0
		for num > 0 {
			size++
			num /= 10
		}
		return size
	}
	res := 0
	for _, num := range nums {
		if getLen(num)%2 == 0 {
			res++
		}
	}
	return res
}
