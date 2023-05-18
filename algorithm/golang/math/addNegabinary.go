package math

func addNegabinary(arr1 []int, arr2 []int) []int {
	convert := func(arr []int) int {
		num := 0
		for _, k := range arr1 {
			num = num*-2 + k
		}
		return num
	}
	num1 := convert(arr1)
	num2 := convert(arr2)
	decode := func(num int) []int {
		if num == 0 {
			return []int{0}
		}
		var arr []int
		for num > 0 {
			if num&0b1 == 0 {
				arr = append(arr, 0)
			} else {
				arr = append(arr, 1)
			}
			num >>= 1
		}
		res := make([]int, len(arr))
		for i, k := range arr {
			res[len(arr)-i-1] = k
		}
		return res
	}
	return decode(num1 + num2)
}
