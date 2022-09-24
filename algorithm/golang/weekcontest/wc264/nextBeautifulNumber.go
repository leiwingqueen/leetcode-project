package wc264

import "math"

func nextBeautifulNumber(n int) int {
	var compose func(num int) []int
	compose = func(num int) []int {
		res := make([]int, 9)
		for i := 0; i < 9; i++ {
			if num&(1<<i) != 0 {
				res[i] = i + 1
			}
		}
		return res
	}
	var find func(c []int, target int) int
	find = func(c []int, target int) int {
		res := -1
		for i := 0; i < 9; i++ {
			if c[i] > 0 {
				c[i]--
				last := target % 10
				var sub int
				if i+1 >= last {
					sub = find(c, target/10)
				} else {
					sub = find(c, target/10+1)
				}
				if sub >= 0 {
					num := sub*10 + i + 1
					if res < 0 || num < res {
						res = num
					}
				}
				c[i]++
			}
		}
		if target == 0 {
			return 0
		}
		return res
	}
	x := math.Log10(float64(n))
	res := math.MaxInt32
	for i := 0; i < (1 << 9); i++ {
		c := compose(i)
		sum := 0
		for j, v := range c {
			if v == 1 {
				sum += j + 1
			}
		}
		if float64(sum) >= x && sum <= 9 {
			r := find(c, n+1)
			if r >= 0 && r < res {
				res = r
			}
		}
	}
	return res
}
