package bwc418

func maxGoodNumber(nums []int) int {
	count := func(num int) int {
		c := 0
		for num > 0 {
			c++
			num >>= 1
		}
		return c
	}
	// 11011
	combine := func(n1, n2, n3 int) int {
		res := 0
		res |= n3
		c := count(n3)
		res |= n2 << c
		c += count(n2)
		res |= n1 << c
		return res
	}
	res := 0
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if j == i {
				continue
			}
			for k := 0; k < 3; k++ {
				if k == i || k == j {
					continue
				}
				res = max(res, combine(nums[i], nums[j], nums[k]))
			}
		}
	}
	return res
}
