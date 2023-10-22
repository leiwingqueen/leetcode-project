package wc368

func minGroupsForValidAssignment(nums []int) int {
	n := len(nums)
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	minCnt := n
	for _, c := range mp {
		if c < minCnt {
			minCnt = c
		}
	}
	expect := minCnt
	for i := expect; i >= 1; i-- {
		res := 0
		flag := true
		for _, c := range mp {
			if c-i <= 1 {
				res++
			} else {
				if c%(i+1) == 0 || c%(i+1) == i || c%(i+1) == i-1 || c%(i+1)+c/(i+1) >= i {
					res += (c + i) / (i + 1)
				} else if c%i == 0 || c%i == 1 {
					res += (c + i - 1) / i
				} else {
					flag = false
					break
				}
			}
		}
		if flag {
			return res
		}
	}
	return -1
}
