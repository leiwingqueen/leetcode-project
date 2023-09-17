package wc363

func sumIndicesWithKSetBits(nums []int, k int) int {
	bitCount := func(num int) int {
		cnt := 0
		for num > 0 {
			if num&0b1 != 0 {
				cnt++
			}
			num >>= 1
		}
		return cnt
	}
	res := 0
	for i, num := range nums {
		if bitCount(i) == k {
			res += num
		}
	}
	return res
}
