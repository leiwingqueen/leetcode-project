package array

import "fmt"

//数7小游戏
func count7(max int) {
	for i := 1; i <= max; i++ {
		if match(i) {
			fmt.Printf("%d\t", i)
		}
	}
	fmt.Printf("\n")
}

func match(num int) bool {
	if num%7 == 0 {
		return true
	}
	for num > 0 {
		if num%10 == 7 {
			return true
		}
		num /= 10
	}
	return false
}
