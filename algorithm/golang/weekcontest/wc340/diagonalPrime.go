package wc340

import "math"

func diagonalPrime(nums [][]int) int {
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
	n := len(nums)
	x, y := 0, 0
	res := 0
	for x < n {
		if isPrime(nums[x][y]) && nums[x][y] > res {
			res = nums[x][y]
		}
		x++
		y++
	}
	x, y = n-1, 0
	for x >= 0 {
		if isPrime(nums[x][y]) && nums[x][y] > res {
			res = nums[x][y]
		}
		x--
		y++
	}
	return res
}
