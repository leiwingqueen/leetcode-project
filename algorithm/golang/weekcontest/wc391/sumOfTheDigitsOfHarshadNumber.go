package wc391

func sumOfTheDigitsOfHarshadNumber(x int) int {
	sum := 0
	num := x
	for x > 0 {
		sum += x % 10
		x /= 10
	}
	if num%sum == 0 {
		return sum
	} else {
		return -1
	}
}
