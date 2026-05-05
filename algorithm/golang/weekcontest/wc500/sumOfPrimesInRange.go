package wc500

import (
	"math"
)

func sumOfPrimesInRange(n int) int {
	isPrime := func(num int) bool {
		if num <= 1 {
			return false
		}
		sqrt := int(math.Sqrt(float64(num)))
		for i := 2; i <= sqrt; i++ {
			if num%i == 0 {
				return false
			}
		}
		return true
	}
	revert := func(num int) int {
		r := 0
		for num > 0 {
			r = r*10 + num%10
			num /= 10
		}
		return r
	}
	res := 0
	l, r := min(n, revert(n)), max(n, revert(n))
	for i := l; i <= r; i++ {
		if isPrime(i) {
			res += i
		}
	}
	return res
}
