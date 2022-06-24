package math

//go用slice比java要省事
func plusOne(digits []int) []int {
	digits[len(digits)-1]++
	if digits[len(digits)-1] < 10 {
		return digits
	}
	digits[len(digits)-1] -= 10
	plus := 1
	for i := len(digits) - 2; i >= 0; i-- {
		if plus == 0 {
			break
		}
		digits[i] += plus
		if digits[i] >= 10 {
			digits[i] -= 10
			plus = 1
		} else {
			plus = 0
		}
	}
	if plus == 1 {
		digits = append([]int{1}, digits...)
	}
	return digits
}
