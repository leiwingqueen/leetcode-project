package bwc98

func minMaxDifference(num int) int {
	// 穷举
	convert := func(n int, from int, to int) int {
		if n == 0 {
			if from == 0 {
				return to
			} else {
				return 0
			}
		}
		res := 0
		pow := 1
		for n > 0 {
			j := n % 10
			if j == from {
				j = to
			}
			res += pow * j
			n /= 10
			pow *= 10
		}
		return res
	}
	max := num
	min := num
	for i := 0; i < 10; i++ {
		for j := 0; j < 10; j++ {
			c := convert(num, i, j)
			if c > max {
				max = c
			}
			if c < min {
				min = c
			}
		}
	}
	return max - min
}
