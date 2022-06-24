package string

import "strconv"

func fizzBuzz(n int) []string {
	list := make([]string, n)
	for i := 1; i <= n; i++ {
		if i%3 != 0 && i%5 != 0 {
			list[i-1] = strconv.Itoa(i)
		} else {
			list[i-1] = ""
			if i%3 == 0 {
				list[i-1] = "Fizz"
			}
			if i%5 == 0 {
				list[i-1] += "Buzz"
			}
		}
	}
	return list
}
