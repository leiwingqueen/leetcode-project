package bwc324

import "fmt"

func smallestValue(n int) int {
	// prime := []int{3, 5, 7, 11}
	convert := func(num int) int {
		res := 0
		for i := 2; i <= num; i++ {
			if num == 1 {
				return res
			}
			for num%i == 0 {
				res += i
				num /= i
			}
		}
		return res
	}
	for true {
		c := convert(n)
		if c == n {
			return c
		}
		n = c
	}
	return -1
}

func calPrimes() {
	isPrime := func(num int) bool {
		for i := 2; i < num; i++ {
			if num%i == 0 {
				return false
			}
		}
		return true
	}
	for i := 2; i <= 100_000; i++ {
		if isPrime(i) {
			fmt.Println(i)
		}
	}
}
