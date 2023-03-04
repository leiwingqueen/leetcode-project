package bwc99

import "math"

func splitNum(num int) int {
	pow := 10
	min := math.MaxInt
	for i := 0; i < 9; i++ {
		if num < pow {
			break
		}
		n1 := num % pow
		n2 := num / pow
		sum := n1 + n2
		if sum < min {
			min = sum
		}
		pow *= 10
	}
	return min
}

func splitNum2(num int) int {
	cnt := make([]int, 10)
	for num > 0 {
		cnt[num%10]++
		num /= 10
	}
	n1 := 0
	n2 := 0
	for i := 0; i < 10; i++ {
		for cnt[i] > 0 {
			if n1 < n2 {
				n1 = n1*10 + i
			} else {
				n2 = n2*10 + i
			}
			cnt[i]--
		}
	}
	return n1 + n2
}
