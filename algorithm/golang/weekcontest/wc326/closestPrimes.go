package wc326

import "math"

// 超时
func closestPrimes(left int, right int) []int {
	check := func(num int) bool {
		if num < 2 {
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
	pre := -1
	res := []int{-1, -1}
	for i := left; i <= right; i++ {
		if check(i) {
			if pre > 0 && (res[0] < 0 || i-pre < (res[1]-res[0])) {
				res[0] = pre
				res[1] = i
			}
			pre = i
		}
	}
	return res
}

// 埃氏筛 预处理
func closestPrimes2(left int, right int) []int {
	var primes []int
	np := make([]bool, right+1)
	for i := 2; i <= right; i++ {
		np[i] = true
	}
	for i := 2; i <= right; i++ {
		if np[i] {
			if i >= left {
				primes = append(primes, i)
			}
			for j := 2 * i; j <= right; j += i {
				np[j] = false
			}
		}
	}
	if len(primes) < 2 {
		return []int{-1, -1}
	}
	res := []int{primes[0], primes[1]}
	for i := 2; i < len(primes); i++ {
		if primes[i]-primes[i-1] < res[1]-res[0] {
			res[0] = primes[i-1]
			res[1] = primes[i]
		}
	}
	return res
}
