package wc393

import "math"

func maximumPrimeDifference(nums []int) int {
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
	l := 0
	for !isPrime(nums[l]) {
		l++
	}
	r := l
	for i := l; i < len(nums); i++ {
		if isPrime(nums[i]) {
			r = i
		}
	}
	return r - l
}
