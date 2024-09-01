package bwc138

func generateKey(num1 int, num2 int, num3 int) int {
	res := 0
	mod := 1
	for i := 0; i < 4; i++ {
		num := min(num1%10, num2%10, num3%10)
		res += mod * num
		mod *= 10
		num1 /= 10
		num2 /= 10
		num3 /= 10
	}
	return res
}
